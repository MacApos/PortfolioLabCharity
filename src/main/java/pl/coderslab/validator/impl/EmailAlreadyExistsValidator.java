package pl.coderslab.validator.impl;

import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EmailAlreadyExists;
import pl.coderslab.validator.Street;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAlreadyExistsValidator implements ConstraintValidator<EmailAlreadyExists, String> {
    private final UserService userService;

    public EmailAlreadyExistsValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(EmailAlreadyExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        User user = userService.findByEmail(value);
        return user == null;
    }
}
