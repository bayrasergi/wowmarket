package mpi.service;

import lombok.AllArgsConstructor;
import mpi.model.Item;
import mpi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemService {
    private ItemRepository itemRepository;

    public List<Item> createItems(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
