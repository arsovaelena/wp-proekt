package mk.ukim.finki.wpproekt.web;

import mk.ukim.finki.wpproekt.model.CommentItem;
import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.service.AuthService;
import mk.ukim.finki.wpproekt.service.CommentService;
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
public class CommentController {
    public final CommentService commentService;
    public final ItemService itemService;
    private final AuthService authService;

    public CommentController(CommentService commentService, ItemService itemService, AuthService authService) {
        this.commentService = commentService;
        this.itemService = itemService;
        this.authService = authService;
    }

    @GetMapping("/comments/{id}/create")
    public String createCommentPage(@PathVariable Long id, Model model)
    {
        Item item = this.itemService.findById(id).get();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = (User) this.authService.loadUserByUsername(username);

        List<CommentItem> commentList = this.commentService.findAllByItemId(id);

        model.addAttribute("item", item);
        model.addAttribute("user", user);
        model.addAttribute("comments", commentList);
        model.addAttribute("bodyContent", "createComment");
        return "master-template";
    }

    @PostMapping("/comments/{id}/add")
    public String addComment(@PathVariable Long id,
                             @RequestParam String username,
                             @RequestParam String comment)
    {
        this.commentService.create(username,id,comment);
        return "redirect:/comments/{id}/create";
    }
}
