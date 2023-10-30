package pl.coderslab.validator.impl;

import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EmailAlreadyExists;
import pl.coderslab.validator.PasswordConfirmed;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (user == null) {
            return true;
        }
       return user.getPassword().equals(user.getPasswordConfirmation());
    }
}
