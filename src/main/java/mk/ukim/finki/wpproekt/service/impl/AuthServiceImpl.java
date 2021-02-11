package mk.ukim.finki.wpproekt.service.impl;

import mk.ukim.finki.wpproekt.exceptions.*;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.repository.UserRepository;
import mk.ukim.finki.wpproekt.service.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import mk.ukim.finki.wpproekt.model.RoleEnumeration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));

    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, RoleEnumeration role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,role);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllByRole(RoleEnumeration role) {
        return this.userRepository.findAllByRole(role);
    }

    @Override
    public void deleteById(String id) {
        this.userRepository.deleteById(id);
    }

}


