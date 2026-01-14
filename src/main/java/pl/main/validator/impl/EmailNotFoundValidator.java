package pl.main.validator.impl;

import pl.main.entity.User;
import pl.main.service.UserService;
import pl.main.validator.EmailNotFound;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailNotFoundValidator implements ConstraintValidator<EmailNotFound, String> {
    private final UserService userService;

    public EmailNotFoundValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(EmailNotFound constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        User user = userService.findByEmail(value);
        return user != null;
    }
}
