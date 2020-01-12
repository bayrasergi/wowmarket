package unit;

import mpi.exception.EntityException;
import mpi.model.Item;
import mpi.model.Lot;
import mpi.model.User;
import mpi.repository.ItemRepository;
import mpi.repository.LotRepository;
import mpi.repository.UserRepository;
import mpi.service.LotService;
import mpi.util.AuthenticationHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LotServiceTest {
    LotService lotService;

    @Mock
    private LotRepository lotRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationHelper authenticationHelper;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        lotService = new LotService(lotRepository, itemRepository, userRepository, authenticationHelper);
    }

    @Test
    void testCreateLotsIsOk() {
        Lot lot = new Lot();
        Item item = new Item();
        item.setName("Name");
        item.setId(1);
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("USER");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        lot.setItem(item);
        lot.setCount(1);
        lot.setPrice(100);
        Mockito.when(authenticationHelper.getCurrentUser()).thenReturn(user);
        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(item));
        lotService.createLots(Collections.singletonList(lot));
    }

    @Test
    void testCreateLotsFailsPriceIsZero() {
        Lot lot = new Lot();
        Item item = new Item();
        item.setName("Name");
        item.setId(1);
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("USER");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        lot.setItem(item);
        lot.setCount(1);
        lot.setPrice(0);
        Mockito.when(authenticationHelper.getCurrentUser()).thenReturn(user);
        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(item));
        try {
            lotService.createLots(Collections.singletonList(lot));
        } catch (EntityException e) {
            assertEquals("Price must be greater than 0!", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testCreateLotsFailsCountIsZero() {
        Lot lot = new Lot();
        Item item = new Item();
        item.setName("Name");
        item.setId(1);
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("USER");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        lot.setItem(item);
        lot.setCount(0);
        lot.setPrice(100);
        Mockito.when(authenticationHelper.getCurrentUser()).thenReturn(user);
        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(item));
        try {
            lotService.createLots(Collections.singletonList(lot));
        } catch (EntityException e) {
            assertEquals("Count must be greater than 0!", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testCreateLotsFailsItemNotFound() {
        Lot lot = new Lot();
        Item item = new Item();
        item.setName("Name");
        item.setId(1);
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("USER");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        lot.setItem(item);
        lot.setCount(1);
        lot.setPrice(100);
        Mockito.when(authenticationHelper.getCurrentUser()).thenReturn(user);
        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.empty());
        try {
            lotService.createLots(Collections.singletonList(lot));
        } catch (EntityException e) {
            assertEquals("Item with id '1' does not exist!", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testCreateLotsFailsUserNotFound() {
        Lot lot = new Lot();
        Item item = new Item();
        item.setName("Name");
        item.setId(1);
        User user = new User();
        user.setId(1);
        lot.setItem(item);
        lot.setCount(1);
        lot.setPrice(100);
        lot.setSellerUser(user);
        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(item));
        try {
            lotService.createLots(Collections.singletonList(lot));
        } catch (EntityException e) {
            assertEquals("User with id '1' does not exist!", e.getMessage());
            return;
        }
        fail();
    }
}
