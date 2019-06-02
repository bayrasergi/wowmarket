package mpi.service;

import lombok.AllArgsConstructor;
import mpi.model.*;
import mpi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestService {

    private RequestRepository requestRepository;
    private RecipeRepository recipeRepository;
    private ItemRepository itemRepository;
    private LotRepository lotRepository;
    private UserRepository userRepository;

    public Request getRequestById(int id) {
        return requestRepository.getOne(id);
    }

    public Request createRequest(Request request) {
        request.setStatus(RequestStatus.CREATED.getStatus());
        return requestRepository.save(request);
    }

    public Request acceptRequest(int requestId, int userId, List<Item> requiredItems) {
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
        Optional<Recipe> recipeOptional = recipeRepository.findByCreatedItem_Id(request.getRequestedItem().getId());
        if (!recipeOptional.isPresent()) {
            return requestRepository.save(request);
        }
        Recipe recipe = recipeOptional.get();
        for (RecipeItem requiredItem : recipe.getRequiredItems()) {
            if (requiredItems.contains(requiredItem.getItem())) {
                Request child = new Request();
                child.setBuyerUser(user);
                child.setCount(requiredItem.getItemsRequired());
                child.setRequestedItem(requiredItem.getItem());
                child.setParentRequest(request);
                createRequest(child);
            }
        }
        return request;
    }

    public Request completeRequest(int requestId, int price) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if (!requestOptional.isPresent()) {
            return null;
        }
        Request request = requestOptional.get();
        request.setStatus(RequestStatus.READY.getStatus());
        for (Request childrenRequest : request.getChildrenRequests()) {
            if (!childrenRequest.getStatus().equals(RequestStatus.CLOSED.getStatus())) {
                closeRequest(childrenRequest.getId());
            }
        }
        Lot lot = new Lot();
        lot.setCount(request.getCount());
        lot.setItem(request.getRequestedItem());
        lot.setSellerUser(request.getSellerUser());
        lot.setPrice(price);
        lot.setComment("Lot created due to request");
        lotRepository.save(lot);
        request.setLot(lot);
        return requestRepository.save(request);
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
            childrenRequest.setStatus(RequestStatus.CLOSED.getStatus());
        }
        requestRepository.saveAll(request.getChildrenRequests());
        request.setStatus(RequestStatus.CLOSED.getStatus());
        return requestRepository.save(request);
    }
}

