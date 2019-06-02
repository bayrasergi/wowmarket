package mpi.service;

import lombok.AllArgsConstructor;
import mpi.model.Certificate;
import mpi.model.Profession;
import mpi.model.Role;
import mpi.model.User;
import mpi.repository.CertificateRepository;
import mpi.repository.ProfessionRepository;
import mpi.repository.RoleRepository;
import mpi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RoleRepository roleRepository;
    private CertificateRepository certificateRepository;
    private ProfessionRepository professionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        Optional<Role> optionalRole = roleRepository.findById(user.getRole().getId());
        if (!optionalRole.isPresent()) {
            return null;
        }
        Role role = optionalRole.get();
        user.setRole(role);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User addCertificate(int userId, int professionId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return null;
        }
        User user = optionalUser.get();
        Optional<Profession> optionalProfession = professionRepository.findById(professionId);
        if (!optionalProfession.isPresent()) {
            return null;
        }
        Profession profession = optionalProfession.get();
        Certificate certificate = new Certificate();
        certificate.setProfession(profession);
        certificate.setUser(user);
        certificateRepository.save(certificate);
        return user;
    }
}
