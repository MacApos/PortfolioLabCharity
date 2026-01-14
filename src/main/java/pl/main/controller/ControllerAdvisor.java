package pl.main.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.main.entity.CurrentUser;
import pl.main.entity.User;
import pl.main.service.UserService;

@ControllerAdvice
public class ControllerAdvisor {
    private final UserService userService;

    public ControllerAdvisor(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("loggedUser")
    public User loggedUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if(currentUser == null){
            return null;
        }
        return userService.findByEmail(currentUser.getUsername());
    }
}
