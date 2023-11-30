package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.User;
import pl.coderslab.service.DonationService;

import java.lang.reflect.Field;

@Controller
public class LoginController {
    private final DonationService donationService;
    private CurrentUser currentUser;

    public LoginController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/welcome-page")
    public String showWelcomePage(@AuthenticationPrincipal CurrentUser currentUser,
                                  Model model) {
        boolean enabled = currentUser.isEnabled();
        boolean enabled1 = currentUser.enabled;
//
//        User user = currentUser.getUser();
//
//
//        int countDonations = donationService.count(user);
//        int sumBagsQuantity = donationService.sumBagsQuantity(user);
//        model.addAttribute("user", user);
//        model.addAttribute("countDonations", countDonations);
//        model.addAttribute("sumBagsQuantity", sumBagsQuantity);
        return "welcomePage";
    }
}
