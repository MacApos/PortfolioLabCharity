package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.groups.Authentication;
import pl.coderslab.validator.groups.NewPassword;
import pl.coderslab.validator.groups.PasswordReset;

@Controller
public class PasswordResetController {
    private final UserService userService;

    public PasswordResetController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/password-reset")
    public String showPasswordResetForm(Model model) {
        model.addAttribute("user", new User());
        return "passwordReset";
    }

    @PostMapping("/password-reset")
    public String processPasswordResetForm(@Validated({PasswordReset.class}) User user, BindingResult result,
                                           Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "passwordReset";
        }
        userService.generateAndEmailToken(model, user);
        return "redirect:/email-authentication";
    }

    @GetMapping("/email-authentication")
    public String showEmailConfirmationForm(Model model) {
        model.addAttribute("user", new User());
        return "emailAuthentication";
    }

    @PostMapping("/email-authentication")
    public String processEmailConfirmationForm(@Validated({Authentication.class}) User user, BindingResult result,
                                               Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "emailAuthentication";
        }
        return "redirect:/new-password";
    }

    @RequestMapping("/new-password")
    public String showNewPasswordForm(Model model) {
        model.addAttribute("user", new User());
        return "newPassword";
    }

    @PostMapping("/new-password")
    public String processNewPasswordForm(@Validated({NewPassword.class}) User user, BindingResult result,
                                             Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "newPassword";
        }
        return "redirect:";
    }
}