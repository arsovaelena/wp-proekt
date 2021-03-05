package mk.ukim.finki.wpproekt.web;

import mk.ukim.finki.wpproekt.enumerations.TypeEnumeration;
import mk.ukim.finki.wpproekt.model.Ingredient;
import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.service.IngredientService;
import mk.ukim.finki.wpproekt.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final IngredientService ingredientService;

    public ItemController(ItemService itemService, IngredientService ingredientService) {
        this.itemService = itemService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String getItems(Model model)
    {
        List<Item> food = this.itemService.findAllByType(TypeEnumeration.FOOD);
        model.addAttribute("food", food);

        List<Item> drink = this.itemService.findAllByType(TypeEnumeration.DRINK);
        model.addAttribute("drink", drink);

        List<Item> desert = this.itemService.findAllByType(TypeEnumeration.DESERT);
        model.addAttribute("desert", desert);

        model.addAttribute("bodyContent", "items");
        return "master-template";
    }

    @GetMapping("/add-form")
    public String getAddForm(Model model)
    {
        model.addAttribute("bodyContent", "createItem");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveItem(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam TypeEnumeration type,
            @RequestParam int price,
            @RequestParam String image) {
        if (id != null) {
            this.itemService.edit(id, name, description, type, price, image);
        } else {
            this.itemService.save(name, description, type, price, image);
        }
        return "redirect:/items";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.itemService.deleteById(id);
        return "redirect:/items";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.itemService.findById(id).isPresent()) {
            Item product = this.itemService.findById(id).get();
            model.addAttribute("item", product);
            model.addAttribute("bodyContent", "createItem");
            return "master-template";
        }
        return "redirect:/items?error=ProductNotFound";
    }


    @GetMapping("/{id}/ingredients")
    public String getIngredients(@PathVariable Long id, Model model)
    {
        Item item = itemService.findById(id).get();
        List<Ingredient> ingredients = ingredientService.findByItem(item);
        model.addAttribute("item", item);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("bodyContent", "ingredientPage");
        return "master-template";
    }

    @GetMapping("/{id}/ingredients/add")
    private String addIngredient(@PathVariable Long id, Model model)
    {
        Item item = this.itemService.findById(id).get();
        model.addAttribute("item", item);
        model.addAttribute("bodyContent", "addIngredient");
        return "master-template";
    }

    @PostMapping("/{id}/ingredients")
    public String saveIngredient(@PathVariable Long id,
                                 @RequestParam String name)
    {
        Item item = this.itemService.findById(id).get();
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
     //   List<Item> items = ingredient.getItems();
        this.ingredientService.save(ingredient);
        this.ingredientService.addIngredientToItem(item,ingredient);
        return "redirect:/items";
    }
}
