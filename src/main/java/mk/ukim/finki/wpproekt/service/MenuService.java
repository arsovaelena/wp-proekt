package mk.ukim.finki.wpproekt.service;

import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.Menu;
import mk.ukim.finki.wpproekt.model.User;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    Optional<Menu> create(String username, Long itemId);
    Optional<Menu> delete(Long id);
    Optional<Menu> findById(Long id);
    List<Menu> findAllByUsername(String username);
}
