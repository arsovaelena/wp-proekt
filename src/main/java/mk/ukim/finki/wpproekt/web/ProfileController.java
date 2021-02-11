package mk.ukim.finki.wpproekt.web;

import mk.ukim.finki.wpproekt.model.RoleEnumeration;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProfileController {

    private final AuthService authService;

    public ProfileController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/profiles")
    public String getEmployees(Model model)
    {
        List<User> employees = this.authService.findAllByRole(RoleEnumeration.EMPLOYEE);
        List<User> managers = this.authService.findAllByRole(RoleEnumeration.MANAGER);

        model.addAttribute("employees", employees);
        model.addAttribute("managers", managers);
        model.addAttribute("bodyContent", "profiles");

        return "master-template";
    }

    @GetMapping("/profiles/create")
    public String getCreatePage(Model model)
    {
        model.addAttribute("bodyContent", "createEmployee");
        return "master-template";
    }

    @PostMapping("/profiles/add")
    public String addEmployee(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String name,
                              @RequestParam String surname)
    {
        RoleEnumeration role = RoleEnumeration.EMPLOYEE;
        String repeatPassword = password;
        this.authService.register(username,password,repeatPassword,name,surname,role);

        return "redirect:/profiles";
    }

    @DeleteMapping("/profiles/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        this.authService.deleteById(id);
        return "redirect:/profiles";
    }
}
