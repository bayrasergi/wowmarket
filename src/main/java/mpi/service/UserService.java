package mpi.service;

import lombok.AllArgsConstructor;
import mpi.exception.EntityException;
import mpi.model.*;
import mpi.repository.CertificateRepository;
import mpi.repository.MessageRepository;
import mpi.repository.ProfessionRepository;
import mpi.repository.UserRepository;
import mpi.util.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    private AuthenticationHelper authenticationHelper;

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

    public List<User> editUsers(List<User> edits) {
        List<User> users = new ArrayList<>();
        edits.forEach(edit -> {
            Optional<User> oUser = userRepository.findById(edit.getId());
            if (!oUser.isPresent()) {
                throw new EntityException(String.format("User with id %d not found!", edit.getId()), HttpStatus.BAD_REQUEST, edits);
            }
            User user = oUser.get();
            User currentUser = authenticationHelper.getCurrentUser();
            if (currentUser.isAdmin() && edit.getLocked() != null) {
                user.setLocked(edit.getLocked());
            }
            if (edit.getPassword() != null && !edit.getPassword().isEmpty()) {
                user.setPassword(bCryptPasswordEncoder.encode(edit.getPassword()));
            }
            if (currentUser.isAdmin() && edit.getRole() != null) {
                Role byName = Role.getByName(edit.getRole());
                if (byName == null) {
                    throw new EntityException(String.format("Role with name %s isn't exist!", edit.getRole().toUpperCase()), HttpStatus.BAD_REQUEST, edits);
                }
                user.setRole(byName.name());
            }
            if (edit.getName() != null && !edit.getName().isEmpty()) {
                user.setName(edit.getName());
            }
            if (edit.getUsername() != null && !edit.getUsername().isEmpty()) {
                User firstByUsername = userRepository.findFirstByUsername(edit.getUsername());
                if (firstByUsername != null) {
                    throw new EntityException(String.format("User with username %s already exist!", edit.getUsername()), HttpStatus.BAD_REQUEST, edits);
                }
                user.setUsername(edit.getUsername());
            }
            users.add(user);
        });
        return users;
    }

    public List<Certificate> getAllCertificates() {
        authenticationHelper.checkRoles(Role.ADMIN);
        return certificateRepository.findAll();
    }

    public List<Certificate> editCertificates(List<Certificate> edits) {
        authenticationHelper.checkRoles(Role.ADMIN);
        List<Certificate> certificates = new ArrayList<>();
        edits.forEach(edit -> {
            Optional<Certificate> optionalCertificate = certificateRepository.findById(edit.getId());
            if (!optionalCertificate.isPresent()) {
                throw new EntityException(String.format("Certificate with id %d not found!", edit.getId()), HttpStatus.BAD_REQUEST, edits);
            }
            Certificate certificate = optionalCertificate.get();
            if (edit.isApproved()) {
                certificate.setApproved(true);
            }
            certificates.add(certificate);
        });
        return certificates;
    }

    public Certificate createCertificate(Certificate certificate) {
        User currentUser = authenticationHelper.getCurrentUser();
        Profession profession = certificate.getProfession();
        if (profession == null || profession.getId() <= 0) {
            throw new EntityException("Profession with id should be specified!", HttpStatus.BAD_REQUEST, certificate);
        }
        Optional<Profession> optionalProfession = professionRepository.findById(profession.getId());
        if (!optionalProfession.isPresent()) {
            throw new EntityException(String.format("Profession with id %d not found!", profession.getId()), HttpStatus.BAD_REQUEST, certificate);
        }
        certificate.setApproved(false);
        certificate.setUser(currentUser);
        certificate.setProfession(optionalProfession.get());
        return certificateRepository.save(certificate);
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
        certificate.setApproved(false);
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
