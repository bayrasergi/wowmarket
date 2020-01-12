package unit;

import mpi.model.Item;
import mpi.repository.ItemRepository;
import mpi.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ItemServiceTest {
    ItemService itemService;

    @Mock
    ItemRepository itemRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        itemService = new ItemService(itemRepository);
    }

    @Test
    void testCreateItemFailsNameIsNull() {
        Item item = new Item();
        try {
            itemService.validateItemName(item);
        } catch (Exception e) {
            assertEquals("Item name must not be null or empty!", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testCreateItemFailsNameExist() {
        Item item = new Item();
        item.setName("Name");
        Mockito.when(itemRepository.findAllByName("Name")).thenReturn(Collections.singletonList(new Item()));
        try {
            itemService.validateItemName(item);
        } catch (Exception e) {
            assertEquals("Item with name 'Name' already exist!", e.getMessage());
            return;
        }
        fail();
    }
}
