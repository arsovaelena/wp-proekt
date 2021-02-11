package mk.ukim.finki.wpproekt.web;

import mk.ukim.finki.wpproekt.model.CommentItem;
import mk.ukim.finki.wpproekt.model.Grade;
import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.service.AuthService;
import mk.ukim.finki.wpproekt.service.CommentService;
import mk.ukim.finki.wpproekt.service.GradeService;
import mk.ukim.finki.wpproekt.service.ItemService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GradeController {
    public final GradeService gradeService;
    public final ItemService itemService;
    private final AuthService authService;

    public GradeController(GradeService gradeService, ItemService itemService, AuthService authService) {
        this.gradeService = gradeService;
        this.itemService = itemService;
        this.authService = authService;
    }

    @GetMapping("/grades/{id}/create")
    public String createGradePage(@PathVariable Long id, Model model) {
        Item item = this.itemService.findById(id).get();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = (User) this.authService.loadUserByUsername(username);

        List<Grade> grades = this.gradeService.findAllByItemId(id);

        model.addAttribute("item", item);
        model.addAttribute("user", user);
        model.addAttribute("grades", grades);
        model.addAttribute("bodyContent", "createGrade");
        return "master-template";
    }

    @PostMapping("/grades/{id}/add")
    public String addGrade(@PathVariable Long id,
                           @RequestParam String username,
                           @RequestParam String grade) {
        this.gradeService.create(username, id, grade);
        return "redirect:/grades/{id}/create";
    }
}