package mpi.util;

import lombok.AllArgsConstructor;
import mpi.exception.EntityException;
import mpi.model.Role;
import mpi.model.User;
import mpi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationHelper {

    private UserRepository userRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findFirstByUsername(username);
    }

    public void checkRoles(Role... roles) {
        List<String> _roles = new ArrayList<>();
        boolean auth = false;
        String currentUserRole = getCurrentUser().getRole();
        for (Role role : roles) {
            String name = role.name();
            if (name.equals(currentUserRole)) {
                auth = true;
            }
            _roles.add(name);
        }
        if (!auth) {
            throw new EntityException("Only user with roles [" + String.join(",", _roles) + "] can perform this operation!", HttpStatus.FORBIDDEN, null);
        }
    }
}
