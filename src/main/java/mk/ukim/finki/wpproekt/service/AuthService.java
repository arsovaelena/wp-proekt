package mk.ukim.finki.wpproekt.service;

import mk.ukim.finki.wpproekt.model.RoleEnumeration;
import mk.ukim.finki.wpproekt.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AuthService {
    User login(String username, String password);

    UserDetails loadUserByUsername(String username);
    User register(String username, String password, String repeatPassword, String name, String surname, RoleEnumeration role);
    List<User> findAllByRole(RoleEnumeration role);
    void deleteById(String id);

}
