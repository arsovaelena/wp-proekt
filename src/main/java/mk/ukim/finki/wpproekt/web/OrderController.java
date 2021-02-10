package mk.ukim.finki.wpproekt.web;

import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.OrderItem;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.service.AuthService;
import mk.ukim.finki.wpproekt.service.ItemService;
import mk.ukim.finki.wpproekt.service.OrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final AuthService authService;
    private final ItemService itemService;
    private final OrderService orderService;

    public OrderController(AuthService authService, ItemService itemService, OrderService orderService) {
        this.authService = authService;
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @GetMapping
    private String orders(Model model)
    {
        List<OrderItem> orderItemList = this.orderService.findAll();
        model.addAttribute("orderList", orderItemList);
        model.addAttribute("bodyContent", "orders");
        return "master-template";
    }

    @GetMapping("/create-order")
    public String create(@RequestParam Long item)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
           username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = (User) this.authService.loadUserByUsername(username);
        Item itemObj = this.itemService.findById(item).get();
        this.orderService.create(user,itemObj);

        return "redirect:/orders";
    }

    @GetMapping("/served-order")
    public String orderDone(@RequestParam Long order)
    {
        OrderItem orderObj = this.orderService.findById(order).get();
        orderObj.setStatus(false);
        return "redirect:/orders";
    }
}
