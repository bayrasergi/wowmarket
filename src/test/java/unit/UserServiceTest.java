package unit;

import mpi.model.User;
import mpi.repository.CertificateRepository;
import mpi.repository.ProfessionRepository;
import mpi.repository.UserRepository;
import mpi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {
    UserService userService;

    @Mock
    UserRepository userRepository;
    @Mock
    CertificateRepository certificateRepository;
    @Mock
    ProfessionRepository professionRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, certificateRepository, professionRepository, bCryptPasswordEncoder);
    }

    @Test
    void testCreateUserPasswordEncoded() {
        String password = "password";
        User user = new User();
        user.setName("name");
        user.setPassword(password);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User _user = userService.createUser(user);
        assertTrue(bCryptPasswordEncoder.matches(password, _user.getPassword()));
    }
}
