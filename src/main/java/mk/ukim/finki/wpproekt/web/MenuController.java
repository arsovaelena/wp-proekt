package mk.ukim.finki.wpproekt.web;

import mk.ukim.finki.wpproekt.enumerations.TypeEnumeration;
import mk.ukim.finki.wpproekt.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    @GetMapping("/create-form/menu")
    public String getMenu(Model model)
    {
        model.addAttribute("bodyContent", "createItem");
        return "master-template";
    }

    @PostMapping("/add/menu")
    public String createMenu(
            @RequestParam(required = false) Long id,
            @RequestParam String username,
            @RequestParam Long itemId) {

        this.menuService.create(username,itemId);
        return "redirect:/items";
    }

    @DeleteMapping("/delete/{id}/menu")
    public String deleteProduct(@PathVariable Long id) {
        this.menuService.delete(id);
        return "redirect:/items";
    }
}
