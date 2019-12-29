package mpi.service;

import lombok.AllArgsConstructor;
import mpi.exception.EntityException;
import mpi.model.Item;
import mpi.model.Lot;
import mpi.model.LotStatus;
import mpi.model.User;
import mpi.repository.ItemRepository;
import mpi.repository.LotRepository;
import mpi.repository.UserRepository;
import mpi.util.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static mpi.model.ItemType.*;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LotService {

    private LotRepository lotRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private AuthenticationHelper authenticationHelper;

    public List<Lot> getAllLots() {
        return lotRepository.findAllByStatus(LotStatus.SELLING.getName());
    }

    public List<Lot> getLotsByUser() {
        return lotRepository.findAllBySellerUser_Id(authenticationHelper.getCurrentUser().getId());
    }

    public List<Lot> createLots(List<Lot> lots) {
        lots.forEach(lot -> {
            validateLotItem(lot);
            validateLotSellerUser(lot);
            if (lot.getCount() <= 0) {
                throw new EntityException("Count must be greater than 0!", HttpStatus.BAD_REQUEST, lot);
            }
            if (lot.getPrice() <= 0) {
                throw new EntityException("Price must be greater than 0!", HttpStatus.BAD_REQUEST, lot);
            }
            lot.setStatus(LotStatus.SELLING.getName());
        });
        return lotRepository.saveAll(lots);
    }

    public Lot deleteLotById(int lotId) {
        if (lotId <= 0) {
            throw new EntityException("Edit must contain lot id!", HttpStatus.BAD_REQUEST, lotId);
        }
        Optional<Lot> oLot = lotRepository.findById(lotId);
        if (!oLot.isPresent()) {
            throw new EntityException(String.format("Lot with id %d not found!", lotId),
                    HttpStatus.BAD_REQUEST, lotId);
        }
        Lot lot = oLot.get();
        User currentUser = authenticationHelper.getCurrentUser();
        if (!currentUser.isAdmin() && lot.getSellerUser().getId() != currentUser.getId()) {
            throw new EntityException("Only seller of the lot or Admin can delete this lot!", HttpStatus.BAD_REQUEST, lot);
        }
        lotRepository.delete(lot);
        return lot;
    }

    public List<Lot> editLots(List<Lot> edits) {
        List<Lot> lots = new ArrayList<>();
        for (Lot lotEdit : edits) {
            if (lotEdit.getId() <= 0) {
                throw new EntityException("Edit must contain lot id!", HttpStatus.BAD_REQUEST, edits);
            }
            Optional<Lot> oLot = lotRepository.findById(lotEdit.getId());
            if (!oLot.isPresent()) {
                throw new EntityException(String.format("Lot with id %d not found!", lotEdit.getId()),
                        HttpStatus.BAD_REQUEST, edits);
            }
            Lot lot = oLot.get();
            if (lot.getStatus().equals(LotStatus.SOLD.name())) {
                continue;
            }
            lots.add(lot);
            User currentUser = authenticationHelper.getCurrentUser();
            if (lot.getSellerUser().getId() == currentUser.getId()) {
                if (lotEdit.getPrice() > 0) {
                    lot.setPrice(lotEdit.getPrice());
                }
                if (lotEdit.getCount() > 0) {
                    lot.setCount(lotEdit.getCount());
                }
                if (lotEdit.getComment() != null) {
                    lot.setComment(lotEdit.getComment());
                }
            }
            if (lotEdit.getStatus() != null && lotEdit.getStatus().equals(LotStatus.SOLD.getName())) {
                lot.setStatus(LotStatus.SOLD.getName());
            }
        }
        return lots;
    }

    public List<Lot> getLotsByItemName(String itemName) {
        List<Lot> byName = lotRepository.findAllByItem_Name(itemName);
        List<Lot> result = new ArrayList<>();
        for (Lot lot : byName) {
            if (lot.getStatus().equals(LotStatus.SELLING.getName())) {
                result.add(lot);
            }
        }
        return result;
    }

    public List<Lot> getLotsByItemType(String itemType) {
        switch (getByName(itemType)) {
            case CRAFTABLE:
                return lotRepository.findAllByItem_Type(CRAFTABLE.getName());
            case COLLECTIBLE:
                return lotRepository.findAllByItem_Type(COLLECTIBLE.getName());
            case STANDARD:
                return lotRepository.findAllByItem_Type(STANDARD.getName());
            default:
                return Collections.emptyList();
        }
    }

    public void validateLotItem(Lot lot) {
        if (lot.getItem() == null) {
            throw new EntityException("Item must not be null!", HttpStatus.BAD_REQUEST, lot);
        }
        Optional<Item> item = itemRepository.findById(lot.getItem().getId());
        if (item.isPresent()) {
            return;
        }
        throw new EntityException("Item with id '" + lot.getItem().getId() + "' does not exist!",
                HttpStatus.BAD_REQUEST, lot);
    }

    public void validateLotSellerUser(Lot lot) {
        User sellerUser = lot.getSellerUser();
        if (sellerUser == null) {
            lot.setSellerUser(authenticationHelper.getCurrentUser());
            return;
        }
        Optional<User> user = userRepository.findById(sellerUser.getId());
        if (user.isPresent()) {
            return;
        }
        throw new EntityException("User with id '" + sellerUser.getId() + "' does not exist!",
                HttpStatus.BAD_REQUEST, lot);
    }
}