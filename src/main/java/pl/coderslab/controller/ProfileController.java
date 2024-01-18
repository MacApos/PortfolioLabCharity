package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.User;
import pl.coderslab.validator.groups.EditUser;

@Controller
@Secured("ROLE_USER")
public class ProfileController {
    @RequestMapping("/edit-my-profile")
    public String showEditMyProfile(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        user.setPassword("");
        model.addAttribute("user", user);
        return "editMyProfile";
    }

    @PostMapping("/edit-my-profile")
    public String processEditMyProfile(@Validated(EditUser.class) User user, BindingResult result, Model model) {
        System.out.println("eidt profile");
        model.addAttribute("user", user);

        if (result.hasErrors()) {
            return "editMyProfile";
        }
//        user.setPassword("");

        return "welcomePage";
    }

}
