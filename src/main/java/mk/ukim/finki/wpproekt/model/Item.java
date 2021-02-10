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

    private String image;

    public Item() {
    }

    public Item(String name, String description, TypeEnumeration type, int price, String image) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeEnumeration getType() {
        return type;
    }

    public void setType(TypeEnumeration type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}