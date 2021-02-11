package mk.ukim.finki.wpproekt.model;
import javax.persistence.*;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;

    @ManyToOne
    private Item item;

    public Menu() {
    }

    public Menu(User user, Item item) {
        this.user= user;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Item getItem() {
        return item;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setItemList(Item itemList) {
        this.item = itemList;
    }
}