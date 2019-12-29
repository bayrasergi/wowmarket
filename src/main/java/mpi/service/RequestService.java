package mpi.service;

import lombok.AllArgsConstructor;
import mpi.exception.EntityException;
import mpi.model.*;
import mpi.repository.*;
import mpi.util.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestService {

    private RequestRepository requestRepository;
    private RecipeRepository recipeRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private CertificateRepository certificateRepository;
    private AuthenticationHelper authenticationHelper;

    public Request createRequest(Request request) {
        Request persist = new Request();
        if (request.getBuyerUser() == null) {
            persist.setBuyerUser(authenticationHelper.getCurrentUser());
        } else {
            persist.setBuyerUser(request.getBuyerUser());
            validateAndSetBuyerUser(persist);
        }
        persist.setRequestedItem(request.getRequestedItem());
        validateAndSetItem(persist);
        persist.setStatus(RequestStatus.CREATED.getStatus());
        int prepayment = (int) (request.getPrice() * 0.1);
        persist.setPrepayment(prepayment > 0 ? prepayment : 1);
        persist.setCount(request.getCount());
        validateCount(persist);
        Request parent = request.getParentRequest();
        if (parent != null && parent.getId() > 0) {
            Optional<Request> oParent = requestRepository.findById(parent.getId());
            if (!oParent.isPresent()) {
                throw new EntityException(String.format("Parent Request with id %d not found!", parent.getId()), HttpStatus.BAD_REQUEST, request);
            }
            persist.setParentRequest(oParent.get());
        } else {
            persist.setParentRequest(null);
        }
        persist = requestRepository.save(persist);
        return persist;
    }

    public List<Request> getRequestsByUser() {
        User currentUser = authenticationHelper.getCurrentUser();
        List<Request> requests = new ArrayList<>();
        List<Request> allByBuyerUser = requestRepository.getAllByBuyerUser(currentUser);
        List<Request> allBySellerUser = requestRepository.getAllBySellerUser(currentUser);
        if (allByBuyerUser != null && !allByBuyerUser.isEmpty()) {
            requests.addAll(allByBuyerUser);
        }
        if (allBySellerUser != null && !allBySellerUser.isEmpty()) {
            requests.addAll(allBySellerUser);
        }
        return requests;
    }

    public List<Request> getRequestsByProfession() {
        User currentUser = authenticationHelper.getCurrentUser();
        return requestRepository.getAllByProfession(currentUser.getId());
    }

    public List<Request> editRequests(List<Request> edits) {
        Set<Request> requests = new HashSet<>();
        User currentUser = authenticationHelper.getCurrentUser();
        edits.forEach(requestEdit -> {
            validateId(requestEdit);
            Optional<Request> oRequest = requestRepository.findById(requestEdit.getId());
            if (!oRequest.isPresent()) {
                throw new EntityException(String.format("Request with id %d not found!", requestEdit.getId()), HttpStatus.BAD_REQUEST, edits);
            }
            Request request = oRequest.get();
            requests.add(request);
            if (requestEdit.getStatus() != null) {
                RequestStatus status = RequestStatus.getStatusByName(requestEdit.getStatus());
                List<Certificate> certificates = certificateRepository.findAllByUser(currentUser);
                if (!currentUser.isAdmin() && certificates.isEmpty()) {
                    throw new EntityException("User must have at least one profession to be able to take request!", HttpStatus.BAD_REQUEST, edits);
                }
                Set<Profession> professions = new HashSet<>();
                certificates.forEach(c -> professions.add(c.getProfession()));
                Optional<Recipe> oRecipe = recipeRepository.findFirstByCreatedItem_Id(request.getRequestedItem().getId());
                if (!oRecipe.isPresent()) {
                    throw new EntityException(String.format("There is no recipe for %s!", request.getRequestedItem().getName()), HttpStatus.BAD_REQUEST, edits);
                }
                Recipe recipe = oRecipe.get();
                if (status != null) {
                    if (currentUser.isAdmin() && status != RequestStatus.CLOSED && status != RequestStatus.DELIVERED
                            && status != RequestStatus.REJECTED) {
                        request.setStatus(status.name());
                    } else if ((request.getBuyerUser().getId() == currentUser.getId())
                            && (status == RequestStatus.WITHDRAWN || status == RequestStatus.CLOSED)) {
                        List<Request> children = setStatusToChildren(request, status);
                        requests.addAll(children);
                        request.setStatus(status.name());
                    } else if (request.getSellerUser() == null && status == RequestStatus.ASSIGNED && professions.contains(recipe.getRequiredProfession())
                            || request.getSellerUser() != null && (status == RequestStatus.READY || status == RequestStatus.CREATED)) {
                        if (request.getSellerUser() == null) {
                            request.setSellerUser(currentUser);
                        }
                        if (status == RequestStatus.CREATED) {
                            List<Request> children = setStatusToChildren(request, RequestStatus.WITHDRAWN);
                            requests.addAll(children);
                        }
                        request.setStatus(status.name());
                    }
                }
            }
            if (requestEdit.getPayment() >= 0) {
                request.setPayment(requestEdit.getPayment());
            }
        });
        return new ArrayList<>(requests);
    }

    public List<Request> setStatusToChildren(Request request, RequestStatus status) {
        List<Request> ret = new ArrayList<>();
        List<Request> childrenRequests = request.getChildrenRequests();
        if (childrenRequests != null && !childrenRequests.isEmpty()) {
            childrenRequests.forEach(child -> {
                child.setStatus(status.name());
                ret.add(child);
                List<Request> childrenOfChild = setStatusToChildren(child, status);
                if (childrenOfChild != null && !childrenOfChild.isEmpty()) {
                    ret.addAll(childrenOfChild);
                }
            });
        }
        return ret;
    }

    public Request acceptRequest(int requestId, int userId, List<Integer> requiredItemsIds) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if (!requestOptional.isPresent()) {
            return null;
        }
        Request request = requestOptional.get();
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return null;
        }
        User user = userOptional.get();
        request.setSellerUser(user);
        request.setStatus(RequestStatus.ASSIGNED.getStatus());
        Optional<Recipe> recipeOptional = recipeRepository.findFirstByCreatedItem_Id(request.getRequestedItem().getId());
        if (!recipeOptional.isPresent() || Objects.isNull(requiredItemsIds) || requiredItemsIds.isEmpty()) {
            return requestRepository.save(request);
        }
        Recipe recipe = recipeOptional.get();
        for (RecipeItem requiredItem : recipe.getRequiredItems()) {
            if (requiredItemsIds.contains(requiredItem.getItem().getId())) {
                Request child = new Request();
                child.setBuyerUser(user);
                child.setCount(requiredItem.getItemsRequired());
                child.setRequestedItem(requiredItem.getItem());
                child.setParentRequest(request);
                createRequest(child);
            }
        }
        return requestRepository.save(request);
    }

    public Request completeRequest(int requestId, int price) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if (!requestOptional.isPresent()) {
            return null;
        }
        Request request = requestOptional.get();
        User currentUser = authenticationHelper.getCurrentUser();
        if (!currentUser.isAdmin() && currentUser.getId() != request.getSellerUser().getId()) {
            throw new EntityException("User must be seller user", HttpStatus.FORBIDDEN, null);
        }
        validatePrice(price);
        request.setPrice(price);
        request.setStatus(RequestStatus.READY.getStatus());
        for (Request childrenRequest : request.getChildrenRequests()) {
            if (!childrenRequest.getStatus().equals(RequestStatus.CLOSED.getStatus())) {
                closeRequest(childrenRequest.getId());
            }
        }
        return request;
    }

    public Request closeRequest(int requestId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if (!requestOptional.isPresent()) {
            return null;
        }
        Request request = requestOptional.get();
        for (Request childrenRequest : request.getChildrenRequests()) {
            if (childrenRequest.getStatus().equals(RequestStatus.CLOSED.getStatus())) {
                continue;
            }
            closeRequest(childrenRequest.getId());
        }
        request.setStatus(RequestStatus.CLOSED.getStatus());
        return request;
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public void validateId(Request request) {
        if (request.getId() <= 0) {
            throw new EntityException("Id must be greater than 0", HttpStatus.BAD_REQUEST, request);
        }
    }

    public void validateAndSetBuyerUser(Request request) {
        if (request.getBuyerUser() == null || request.getBuyerUser().getId() <= 0) {
            throw new EntityException("Buyer user id must be greater than 0", HttpStatus.BAD_REQUEST, request);
        }
        Optional<User> oUser = userRepository.findById(request.getBuyerUser().getId());
        if (oUser.isPresent()) {
            request.setBuyerUser(oUser.get());
            return;
        }
        throw new EntityException(String.format("User with id %d not found!", request.getBuyerUser().getId()),
                HttpStatus.BAD_REQUEST, request);
    }

    public void validateAndSetSellerUser(Request request) {
        if (request.getSellerUser() == null || request.getSellerUser().getId() <= 0) {
            throw new EntityException("Seller user id must be greater than 0", HttpStatus.BAD_REQUEST, request);
        }
        Optional<User> oUser = userRepository.findById(request.getSellerUser().getId());
        if (oUser.isPresent()) {
            request.setSellerUser(oUser.get());
            return;
        }
        throw new EntityException(String.format("User with id %d not found!", request.getSellerUser().getId()),
                HttpStatus.BAD_REQUEST, request);
    }

    public void validateAndSetItem(Request request) {
        if (request.getRequestedItem() == null || request.getRequestedItem().getId() <= 0) {
            throw new EntityException("Requested item id must be greater than 0", HttpStatus.BAD_REQUEST, request);
        }
        Optional<Item> oItem = itemRepository.findById(request.getRequestedItem().getId());
        if (oItem.isPresent()) {
            request.setRequestedItem(oItem.get());
            return;
        }
        throw new EntityException(String.format("Item with id %d not found", request.getRequestedItem().getId()),
                HttpStatus.BAD_REQUEST, request);
    }

    public void validatePrice(Request request) {
        validatePrice(request.getPrice());
    }

    public void validatePrice(int price) {
        if (price <= 0) {
            throw new EntityException("Price must be greater than 0", HttpStatus.BAD_REQUEST, price);
        }
    }

    public void validateCount(Request request) {
        if (request.getCount() <= 0) {
            throw new EntityException("Count must be greater than 0", HttpStatus.BAD_REQUEST, request);
        }
    }
}

