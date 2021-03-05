package mk.ukim.finki.wpproekt.service;

import mk.ukim.finki.wpproekt.model.Ingredient;
import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    List<Ingredient> findAll();
    Optional<Ingredient> findById(Long id);
    List<Ingredient> findByItem(Item item);
    Item addIngredientToItem(Item item, Ingredient ingredient);
    Ingredient save(Ingredient ingredient);
}
