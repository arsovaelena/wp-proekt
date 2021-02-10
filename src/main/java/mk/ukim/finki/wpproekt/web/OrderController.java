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
import org.springframework.web.bind.annotation.*;

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

        List<String> users = this.orderService.usersThatOrdered();
        model.addAttribute("users", users);
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

        return "redirect:/items";
    }

    @GetMapping("/served-order")
    public String orderDone(@RequestParam(required = false) String username,
                            @RequestParam(required = false) String paid)
    {
        if(username!=null && paid == null)
        {
            List<OrderItem> userOrders = this.orderService.findAllByUserAndStatus(username, true);
            int suma = 0;

            userOrders.forEach((order)->order.setStatus(false));
            userOrders.forEach(orderService::save);

            suma = userOrders.stream().mapToInt(order->order.getItem().getPrice()).sum();


            return "redirect:/orders?suma="+suma;
        }
        else if(username!=null && paid.equals("yes"))
        {
            this.orderService.deleteAllByUser(username);
            return "redirect:/orders";
        }
        else
            return "redirect:/orders";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.orderService.deleteById(id);
        return "redirect:/orders";
    }
}
