package mk.ukim.finki.wpproekt.service;

import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.OrderItem;
import mk.ukim.finki.wpproekt.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderItem create(User user, Item item);
    List<OrderItem> findAll();
    Optional<OrderItem> findById(Long id);
    void deleteById(Long id);
    List<String> usersThatOrdered();
    List<OrderItem> findAllByUserAndStatus(String username, boolean status);
    OrderItem save(OrderItem orderItem);
    void deleteAllByUser(String username);
}
