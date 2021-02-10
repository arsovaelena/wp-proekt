package mk.ukim.finki.wpproekt.web;

import mk.ukim.finki.wpproekt.enumerations.TypeEnumeration;
import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;


    public ItemController(ItemService itemService) {
        this.itemService = itemService;
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

}
