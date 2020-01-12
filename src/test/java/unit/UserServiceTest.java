package unit;

import mpi.exception.EntityException;
import mpi.model.Certificate;
import mpi.model.Profession;
import mpi.model.User;
import mpi.repository.CertificateRepository;
import mpi.repository.MessageRepository;
import mpi.repository.ProfessionRepository;
import mpi.repository.UserRepository;
import mpi.service.UserService;
import mpi.util.AuthenticationHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserService userService;

    @Mock
    UserRepository userRepository;
    @Mock
    CertificateRepository certificateRepository;
    @Mock
    ProfessionRepository professionRepository;
    @Mock
    MessageRepository messageRepository;
    @Mock
    AuthenticationHelper authenticationHelper;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, certificateRepository, professionRepository, messageRepository, bCryptPasswordEncoder, authenticationHelper);
    }

    @Test
    void testCreateUserPasswordEncoded() {
        String password = "password";
        User user = new User();
        user.setName("name");
        user.setUsername("username");
        user.setRole("USER");
        user.setPassword(password);
        Mockito.when(userRepository.findFirstByUsername("username")).thenReturn(null);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User _user = userService.createUser(user);
        assertTrue(bCryptPasswordEncoder.matches(password, _user.getPassword()));
    }

    @Test
    void testEditUserIsOk() {
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("USER");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setLocked(false);
        User edits = new User();
        edits.setUsername("new_username");
        edits.setName("new name");
        edits.setPassword("new password");
        Mockito.when(userRepository.findFirstByUsername("new_username")).thenReturn(null);
        Mockito.when(authenticationHelper.getCurrentUser()).thenReturn(user);
        userService.editUser(edits);
        assertTrue(bCryptPasswordEncoder.matches("new password", user.getPassword()));
        assertEquals(edits.getUsername(), user.getUsername());
        assertEquals(edits.getName(), user.getName());
    }

    @Test
    void testEditUserUsernameFails() {
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("USER");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setLocked(false);
        User edits = new User();
        edits.setUsername("new_username");
        Mockito.when(authenticationHelper.getCurrentUser()).thenReturn(user);
        Mockito.when(userRepository.findFirstByUsername("new_username")).thenReturn(new User());
        try {
            userService.editUser(edits);
        } catch (EntityException e) {
            assertEquals("User with username new_username already exist!", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testEditUserFailsUserNotFound() {
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("USER");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setLocked(false);
        User edits = new User();
        edits.setId(2);
        edits.setUsername("new_username");
        Mockito.when(authenticationHelper.getCurrentUser()).thenReturn(user);
        Mockito.when(userRepository.findFirstByUsername("new_username")).thenReturn(new User());
        try {
            userService.editUser(edits);
        } catch (EntityException e) {
            assertEquals("User with id 2 not found!", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testEditUserFailsRoleDoesNotExist() {
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("ADMIN");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setLocked(false);
        User user2 = new User();
        user2.setId(2);
        user2.setName("name");
        user2.setUsername("username2");
        user2.setRole("USER");
        user2.setPassword(bCryptPasswordEncoder.encode("password"));
        user2.setLocked(false);
        User edits = new User();
        edits.setId(2);
        edits.setRole("TOLE");
        Mockito.when(authenticationHelper.getCurrentUser()).thenReturn(user);
        Mockito.when(userRepository.findFirstByUsername("new_username")).thenReturn(new User());
        Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(user2));
        try {
            userService.editUser(edits);
        } catch (EntityException e) {
            assertEquals("Role with name TOLE isn't exist!", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testCreateCertificateFailsProfessionIsNull() {
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("ADMIN");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setLocked(false);
        Mockito.when(authenticationHelper.getCurrentUser()).thenReturn(user);
        Profession profession = new Profession();
        profession.setId(1);
        profession.setName("prof");
        profession.setCraft(false);
        Certificate certificate = new Certificate();
        try {
            userService.createCertificate(certificate);
        } catch (EntityException e) {
            assertEquals("Profession with id should be specified!", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testCreateCertificateFailsProfessionNotFound() {
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("ADMIN");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setLocked(false);
        Mockito.when(authenticationHelper.getCurrentUser()).thenReturn(user);
        Profession profession = new Profession();
        profession.setId(1);
        profession.setName("prof");
        profession.setCraft(false);
        Certificate certificate = new Certificate();
        certificate.setProfession(profession);
        try {
            userService.createCertificate(certificate);
        } catch (EntityException e) {
            assertEquals("Profession with id 1 not found!", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testCreateCertificateIsOk() {
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("ADMIN");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setLocked(false);
        Mockito.when(authenticationHelper.getCurrentUser()).thenReturn(user);
        Profession profession = new Profession();
        profession.setId(1);
        profession.setName("prof");
        profession.setCraft(false);
        Certificate certificate = new Certificate();
        certificate.setProfession(profession);
        Mockito.when(professionRepository.findById(1)).thenReturn(Optional.of(profession));
    }

    @Test
    void testAddCertificateFailsUserNotFound() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());
        try {
            userService.addCertificate(1, "prof");
        } catch (EntityException e) {
            assertEquals("User with id 1 does not found!", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testAddCertificateFailsProfessionNotFound() {
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("ADMIN");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setLocked(false);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        try {
            userService.addCertificate(1, "prof");
        } catch (EntityException e) {
            assertEquals("There is no 'prof' profession!", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testAddCertificateIsOk() {
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setUsername("username");
        user.setRole("ADMIN");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setLocked(false);
        Profession profession = new Profession();
        profession.setId(1);
        profession.setName("prof");
        profession.setCraft(false);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(professionRepository.findAllByName("prof")).thenReturn(Collections.singletonList(profession));
        userService.addCertificate(1, "prof");
    }
}
