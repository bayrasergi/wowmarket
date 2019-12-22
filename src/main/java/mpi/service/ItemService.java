package mpi.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import mpi.deserializer.ItemDeserializer;
import mpi.exception.EntityException;
import mpi.model.Item;
import mpi.model.ItemType;
import mpi.repository.ItemRepository;
import mpi.serializer.ItemSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static mpi.model.ItemType.*;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
@JsonSerialize(using = ItemSerializer.class)
@JsonDeserialize(using = ItemDeserializer.class)
public class ItemService {
    private ItemRepository itemRepository;

    public List<Item> createItems(List<Item> items) {
        items.forEach(this::validateItemName);
        return itemRepository.saveAll(items);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getAllByItemType(String type) {
        return itemRepository.findAllByType(type.toUpperCase());
    }

    public void validateItemName(Item item) {
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new EntityException("Item name must not be null or empty!", HttpStatus.BAD_REQUEST, item);
        }
        List<Item> allByName = itemRepository.findAllByName(item.getName());
        if (!allByName.isEmpty()) {
            throw new EntityException(String.format("Item with name '%s' already exist!", item.getName()),
                    HttpStatus.BAD_REQUEST, item);
        }
    }
}
