package mk.ukim.finki.wpproekt.repository;

import mk.ukim.finki.wpproekt.model.Ingredient;
import mk.ukim.finki.wpproekt.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    List<Ingredient> findByItems(List<Item> itemId);
}
