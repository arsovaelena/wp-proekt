package mk.ukim.finki.wpproekt.service.impl;

import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.OrderItem;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.repository.OrderRepository;
import mk.ukim.finki.wpproekt.service.OrderService;
import org.springframework.stereotype.Service;

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
}
