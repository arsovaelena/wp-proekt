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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Character getGrade() {
        return grade;
    }

    public void setGrade(Character grade) {
        this.grade = grade;
    }

    public Grade(User user, Item item, Character gradeChar) {
        this.user = user;
        this.item = item;
        this.grade = grade;
    }
}