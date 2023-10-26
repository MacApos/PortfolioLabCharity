package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.User;
import pl.coderslab.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
    private final InstitutionService institutionService;

    public AdminController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("/admin-page")
    public String showAdminPage(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User admin = customUser.getUser();
        model.addAttribute("user", admin);
        return "adminPage";
    }
}