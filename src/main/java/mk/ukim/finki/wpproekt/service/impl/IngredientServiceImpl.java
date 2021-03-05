package mk.ukim.finki.wpproekt.service.impl;

import mk.ukim.finki.wpproekt.model.Ingredient;
import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.repository.IngredientRepository;
import mk.ukim.finki.wpproekt.repository.ItemRespository;
import mk.ukim.finki.wpproekt.service.IngredientService;
import mk.ukim.finki.wpproekt.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ItemService itemService;
    private final ItemRespository itemRespository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, ItemService itemService, ItemRespository itemRespository) {
        this.ingredientRepository = ingredientRepository;
        this.itemService = itemService;
        this.itemRespository = itemRespository;
    }

    @Override
    public List<Ingredient> findAll() {
        return this.ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        return Optional.of(this.ingredientRepository.findById(id).get());
    }

    @Override
    public List<Ingredient> findByItem(Item item) {
        return ingredientRepository.findAllByItemsContaining(item);
    }

    @Override
    public Item addIngredientToItem(Item item, Ingredient ingredient) {
        item.getIngredients().add(ingredient);
        return this.itemRespository.save(item);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        this.ingredientRepository.save(ingredient);
        return ingredient;
    }
}
