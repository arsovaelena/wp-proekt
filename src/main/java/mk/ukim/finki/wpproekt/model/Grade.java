package mk.ukim.finki.wpproekt.model;

import javax.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Item item;

    private Character grade;

    public Grade() {
    }

    public Grade(Long id, User user, Item item, Character grade) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.grade = grade;
    }
}
