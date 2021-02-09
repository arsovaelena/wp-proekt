package mk.ukim.finki.wpproekt.model;
import mk.ukim.finki.wpproekt.enumerations.RoleEnumeration;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnumeration role;

    public User() {
    }

    public User(String username, String password, RoleEnumeration role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}