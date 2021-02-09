package mk.ukim.finki.wpproekt.repository;

import mk.ukim.finki.wpproekt.model.OrderItem;
import mk.ukim.finki.wpproekt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByUserAndStatus(User user, boolean status);
}
