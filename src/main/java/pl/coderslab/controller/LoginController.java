package pl.coderslab.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.entity.User;
import pl.coderslab.security.SpringDataUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {
    private final SpringDataUserDetailsService springDataUserDetailsService;

    public LoginController(SpringDataUserDetailsService springDataUserDetailsService) {
        this.springDataUserDetailsService = springDataUserDetailsService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/process-login")
    public String processLoginForm(User user, Model model) {
        String email = user.getEmail();
        String password = user.getPassword();
        if (email == null || email.isBlank()) {
            model.addAttribute("emptyEmail", "Wpisz adres email.");
            return "login";
        }
        UserDetails userDetails;
        try {
            userDetails = springDataUserDetailsService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            model.addAttribute("emailNotFound", "Nie znaleziono konta.");
            return "login";
        }
        if (!BCrypt.checkpw(password, userDetails.getPassword())) {
            model.addAttribute("wrongPassword", "Niepoprawne hasło.");
            return "login";
        }
        ControllerAdvisor.username = userDetails.getUsername();
        return "redirect:";
    }
}
