package mk.ukim.finki.wpproekt.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    private boolean status;

    public OrderItem() {
    }

    public OrderItem(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.status = true;
    }
}
