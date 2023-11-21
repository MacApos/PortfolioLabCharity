package pl.coderslab.validator.impl;

import pl.coderslab.entity.Role;
import pl.coderslab.entity.User;
import pl.coderslab.validator.City;
import pl.coderslab.validator.PasswordConfirmed;
import pl.coderslab.validator.UserIsAdmin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.Set;

public class UserIsAdminValidator implements ConstraintValidator<UserIsAdmin, User> {
    @Override
    public void initialize(UserIsAdmin constraintAnnotation) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (user == null) {
            return true;
        }
        Set<Role> roles = user.getRoles();
        return roles.stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
    }
}
