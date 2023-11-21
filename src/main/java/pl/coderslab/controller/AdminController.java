package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.groups.NewAdmin;
import pl.coderslab.validator.groups.Registration;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin-page")
    public String showAdminPage(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User admin = customUser.getUser();
        model.addAttribute("user", admin);
        return "welcomePage";
    }

    @GetMapping("/show-admins")
    public String showAdmins(Model model) {
        return "showAdmins";
    }

    @RequestMapping("/edit-admin")
    public String showAdminForm(@RequestParam(required = false) Long id, Model model) {
        User admin = userService.findById(id);
        model.addAttribute("admin", admin);
        return "editAdmin";
    }

    @PostMapping("/edit-admin")
    public String processAdminForm(@Valid User admin, BindingResult result, Model model) {
        model.addAttribute("admin", admin);
        if (result.hasErrors()) {
            return "editAdmin";
        }
        userService.update(admin);
        return "redirect:show-admins";
    }

    @RequestMapping("/delete-admin")
    public String deleteAdmin(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam Long id) {
        userService.deleteById(currentUser, id);
        return "showAdmins";
    }

    @RequestMapping("/add-admin")
    public String showAddAdminForm(Model model) {
        model.addAttribute("admin", new User());
        return "addAdmin";
    }

    @PostMapping("/add-admin")
    public String processAddAdminForm(@Validated({NewAdmin.class}) User admin, BindingResult result,
                                      Model model) {
        model.addAttribute("admin", admin);
        if (result.hasErrors()) {
            return "addAdmin";
        }
        return "showAdmins";
    }

    @ModelAttribute("admins")
    public List<User> findAllAdmins() {
        return userService.findByRole("ROLE_ADMIN");
    }

    @ModelAttribute("users")
    public List<User> findAllUsers() {
        return userService.findByRole("ROLE_USER");
    }

    @ModelAttribute("currentUser")
    public User findCurrentUser(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        return findAllAdmins().stream().filter(u -> userService.isCurrentUser(currentUser, u))
                .findFirst().orElse(null);
    }

}
