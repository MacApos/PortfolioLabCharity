package pl.coderslab.validator.impl;

import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EmailAlreadyExists;
import pl.coderslab.validator.Street;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAlreadyExistsValidator implements ConstraintValidator<EmailAlreadyExists, User> {
    private final UserService userService;

    public EmailAlreadyExistsValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(EmailAlreadyExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (user == null) {
            return true;
        }
        Long id = user.getId();
        String userEmail = user.getEmail();

        User existingUser;
        if (id == null) {
            existingUser = userService.findByEmail(userEmail);
            return existingUser == null;
        }
        existingUser = userService.findById(id);
        String existingUserEmail = existingUser.getEmail();
        if (existingUserEmail.equals(userEmail)) {
            return true;
        }
        return userService.findByEmail(userEmail) == null;
    }

    public boolean isValid2(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (user == null) {
            return true;
        }
        User exisitngUser = userService.findByEmail(user.getEmail());
        return exisitngUser == null;
    }

}
