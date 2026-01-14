package pl.main.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.main.entity.CurrentUser;
import pl.main.entity.User;
import pl.main.service.UserService;
import pl.main.validator.groups.EditUser;


@Controller
@Secured("ROLE_USER")
public class ProfileController {
    private final UserService userService;
    private final Validator validator;

    public ProfileController(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @RequestMapping("/edit-my-profile")
    public String showEditMyProfile(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        user.setPassword(null);
        model.addAttribute("user", user);
        return "editMyProfile";
    }

    @PostMapping("/edit-my-profile")
    public String processEditMyProfile(@Validated({EditUser.class}) User user, BindingResult result, Model model) {
        model.addAttribute("user", user);
        System.out.println();
        if (result.hasErrors()) {
            return "editMyProfile";
        }
        return "welcomePage";
    }

}
