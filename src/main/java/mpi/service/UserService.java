package mpi.service;

import lombok.AllArgsConstructor;
import mpi.exception.EntityException;
import mpi.model.*;
import mpi.repository.CertificateRepository;
import mpi.repository.MessageRepository;
import mpi.repository.ProfessionRepository;
import mpi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;
    private CertificateRepository certificateRepository;
    private ProfessionRepository professionRepository;
    private MessageRepository messageRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        validateUsername(user);
        validatePassword(user);
        validateName(user);
        user.setRole(Role.getByName(user.getRole()).getName());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User addCertificate(int userId, String professionName) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return null;
        }
        User user = optionalUser.get();
        List<Profession> professions = professionRepository.findAllByName(professionName);
        if (professions.isEmpty()) {
            throw new EntityException(String.format("There is no '%s' profession!", professionName),
                    HttpStatus.BAD_REQUEST, professionName);
        }
        Certificate certificate = new Certificate();
        certificate.setProfession(professions.get(0));
        certificate.setUser(user);
        certificateRepository.save(certificate);
        return user;
    }

    public List<Message> getMessages(int userId) {
        return messageRepository.findAllByUser_Id(userId);
    }

    public Message createMessage(int userId, Message message) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            message.setUser(user.get());
            return messageRepository.save(message);
        }
        throw new EntityException("User is not found!", HttpStatus.BAD_REQUEST, message);
    }

    private void validateName(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new EntityException("Name must not be null or empty!", HttpStatus.BAD_REQUEST, user);
        }
    }

    private void validateUsername(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new EntityException("Username must not be null or empty!", HttpStatus.BAD_REQUEST, user);
        }
    }

    private void validatePassword(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new EntityException("Password must not be null or empty!", HttpStatus.BAD_REQUEST, user);
        }
    }
}
