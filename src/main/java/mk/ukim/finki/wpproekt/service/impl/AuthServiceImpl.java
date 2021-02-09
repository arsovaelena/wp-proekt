package mk.ukim.finki.wpproekt.service.impl;

import mk.ukim.finki.wpproekt.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wpproekt.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.repository.UserRepository;
import mk.ukim.finki.wpproekt.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}


