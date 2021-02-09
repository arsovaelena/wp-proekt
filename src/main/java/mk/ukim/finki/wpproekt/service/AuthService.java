package mk.ukim.finki.wpproekt.service;

import mk.ukim.finki.wpproekt.model.User;

public interface AuthService {
    User login(String username, String password);
}
