package pl.main.validator.impl;

import pl.main.entity.Role;
import pl.main.entity.User;
import pl.main.service.UserService;
import pl.main.validator.UserIsAlreadyAdmin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class UserIsAlreadyAdminValidator implements ConstraintValidator<UserIsAlreadyAdmin, User> {
    private final UserService userService;

    public UserIsAlreadyAdminValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UserIsAlreadyAdmin constraintAnnotation) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (user == null) {
            return true;
        }
        User existingUser = userService.findByEmail(user.getEmail());
        Set<Role> roles = existingUser.getRoles();
        return roles.stream().noneMatch(role -> role.getName().equals("ROLE_ADMIN"));
    }
}
