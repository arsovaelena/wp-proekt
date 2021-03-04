package mk.ukim.finki.wpproekt.service.impl;

import mk.ukim.finki.wpproekt.model.Ingredient;
import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.repository.IngredientRepository;
import mk.ukim.finki.wpproekt.service.IngredientService;
import mk.ukim.finki.wpproekt.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ItemService itemService;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, ItemService itemService) {
        this.ingredientRepository = ingredientRepository;
        this.itemService = itemService;
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
    public List<Ingredient> findByItems(List<Long> itemId) {
        List<Item> items = this.itemService.findAllById(itemId);
        return Optional.of(this.ingredientRepository.findByItems(items)).get();
    }
}
