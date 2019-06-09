package mpi.service;

import lombok.AllArgsConstructor;
import mpi.model.User;
import mpi.model.UserPrincipal;
import mpi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserPrincipalService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(s);
        if (Objects.isNull(user)) {
            throw new RuntimeException("User not found!");
        }
        return new UserPrincipal(user);
    }
}
