package pl.main.validator.impl;

import pl.main.entity.User;
import pl.main.validator.PasswordConfirmed;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, User> {
    @Override
    public void initialize(PasswordConfirmed constraintAnnotation) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (user == null) {
            return true;
        }

       return user.getPasswordConfirmation().equals(user.getPassword());
    }
}
