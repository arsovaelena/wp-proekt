package mk.ukim.finki.wpproekt.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Item item;

    private Date created_at;

    private boolean status;

    public Order() {
    }

    public Order(Long id, User user, Item item, Date created_at, boolean status) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.created_at = created_at;
        this.status = status;
    }
}
