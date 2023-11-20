package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.User;
import pl.coderslab.service.InstitutionService;
import pl.coderslab.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
    private final InstitutionService institutionService;
    private final UserService userService;

    public AdminController(InstitutionService institutionService, UserService userService) {
        this.institutionService = institutionService;
        this.userService = userService;
    }

    @GetMapping("/admin-page")
    public String showAdminPage(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User admin = customUser.getUser();
        model.addAttribute("user", admin);
        return "welcomePage";
    }

    @GetMapping("/show-admins")
    public String showAdmins(Model model){
        List<User> admins = userService.findByRole("ROLE_ADMIN");
        model.addAttribute("admins", admins);
        return "showAdmins";
    }
}
