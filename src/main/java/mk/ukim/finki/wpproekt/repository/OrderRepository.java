package mk.ukim.finki.wpproekt.repository;

import mk.ukim.finki.wpproekt.model.OrderItem;
import mk.ukim.finki.wpproekt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByUserAndStatus(User user, boolean status);
    public static final String FIND_USERS = "SELECT DISTINCT user_username FROM order_item";

    @Query(value = FIND_USERS, nativeQuery = true)
    public List<String> findAllUsersThatOrdered();

    public List<OrderItem> findAllByUser_UsernameAndStatus(String username, boolean status);

    void deleteAllByUser_Username(String username);
}
