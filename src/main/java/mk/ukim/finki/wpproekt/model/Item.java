package mk.ukim.finki.wpproekt.model;

import mk.ukim.finki.wpproekt.enumerations.TypeEnumeration;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private TypeEnumeration type;

    private int price;

    public Item() {
    }

    public Item(Long id, String name, String description, TypeEnumeration type, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
    }
}
