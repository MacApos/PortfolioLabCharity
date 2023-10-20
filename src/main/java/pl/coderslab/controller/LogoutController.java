package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout() {
        return "admin/logout";
    }

    @PostMapping("/logout")
    public String redirectToHome() {
        return "redirect:..";
    }
}
