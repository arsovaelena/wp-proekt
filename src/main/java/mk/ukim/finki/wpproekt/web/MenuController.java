package mk.ukim.finki.wpproekt.web;

import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.Menu;
import mk.ukim.finki.wpproekt.service.ItemService;
import mk.ukim.finki.wpproekt.service.MenuService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MenuController {

    private final MenuService menuService;
    private final ItemService itemService;

    public MenuController(MenuService menuService, ItemService itemService) {
        this.menuService = menuService;
        this.itemService = itemService;
    }

    @GetMapping("/your-menu")
    public String seeMenu(Model model)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        List<Menu> menuList = this.menuService.findAllByUsername(username);
        model.addAttribute("menuList", menuList);
        model.addAttribute("bodyContent", "menu");
        return "master-template";
    }

    @GetMapping("/create-form/menu")
    public String getMenu(Model model)
    {
        List<Item> items = this.itemService.findAll();
        model.addAttribute("bodyContent", "createMenu");
        model.addAttribute("items",items);
        return "master-template";
    }

    @PostMapping("/add/menu")
    public String createMenu(@RequestParam Long item) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        this.menuService.create(username,item);
        return "redirect:/create-form/menu";
    }

    @DeleteMapping("/delete/{id}/menu")
    public String deleteProduct(@PathVariable Long id) {
        this.menuService.delete(id);
        return "redirect:/your-menu";
    }
}
