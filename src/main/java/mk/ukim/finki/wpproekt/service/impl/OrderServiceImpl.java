package mk.ukim.finki.wpproekt.service.impl;

import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.OrderItem;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.repository.OrderRepository;
import mk.ukim.finki.wpproekt.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    public final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderItem create(User user, Item item) {
        return this.orderRepository.save(new OrderItem(user,item));
    }

    @Override
    public List<OrderItem> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.orderRepository.deleteById(id);
    }

    @Override
    public List<String> usersThatOrdered() {
        return this.orderRepository.findAllUsersThatOrdered();
    }

    @Override
    public List<OrderItem> findAllByUserAndStatus(String username, boolean status) {
        return this.orderRepository.findAllByUser_UsernameAndStatus(username, status);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return this.orderRepository.save(orderItem);
    }

    @Override
    @Transactional
    public void deleteAllByUser(String username) {
        this.orderRepository.deleteAllByUser_Username(username);
    }
}
