package mpi.service;

import lombok.AllArgsConstructor;
import mpi.exception.EntityException;
import mpi.model.Item;
import mpi.model.Lot;
import mpi.model.User;
import mpi.repository.ItemRepository;
import mpi.repository.LotRepository;
import mpi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LotService {
    private LotRepository lotRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    public List<Lot> createLots(List<Lot> lots) {
        lots.forEach(lot -> {
            validateLotItem(lot);
            validateLotSellerUser(lot);
        });
        return lotRepository.saveAll(lots);
    }

    public List<Lot> getLotsByItemId(int itemId) {
        return lotRepository.findAllByItem_Id(itemId);
    }

    public List<Lot> getLotsBySellerUserId(int userId) {
        return lotRepository.findAllByItem_Id(userId);
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
            throw new EntityException("Seller user must not be null!", HttpStatus.BAD_REQUEST, lot);
        }
        Optional<User> user = userRepository.findById(sellerUser.getId());
        if (user.isPresent()) {
            return;
        }
        throw new EntityException("User with id '" + sellerUser.getId() + "' does not exist!",
                HttpStatus.BAD_REQUEST, lot);
    }
}
