package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.User;
import pl.coderslab.security.SpringDataUserDetailsService;
import pl.coderslab.service.UserService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/welcome-page")
    public String showUserPage(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = customUser.getUser();
        model.addAttribute("user", user);
        return "welcomePage";
    }
}
