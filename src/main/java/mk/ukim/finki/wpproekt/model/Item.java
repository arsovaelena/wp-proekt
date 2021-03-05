package mk.ukim.finki.wpproekt.model;

import mk.ukim.finki.wpproekt.enumerations.TypeEnumeration;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToMany(mappedBy = "items")
    private List<Ingredient> ingredients;

    public Item() {
    }

    public Item(String name, String description, TypeEnumeration type, int price, String image) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.image = image;
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Item(String name, String description, TypeEnumeration type, int price, String image, List<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.image = image;
        this.ingredients = ingredients;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}