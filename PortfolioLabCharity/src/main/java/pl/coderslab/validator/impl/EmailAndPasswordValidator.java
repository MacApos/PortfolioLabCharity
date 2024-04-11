package pl.coderslab.validator.impl;

import org.springframework.beans.BeanWrapperImpl;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EmailAndPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAndPasswordValidator implements ConstraintValidator<EmailAndPassword, Object> {
    private String userId;
    private String firstField;
    private String secondField;
    private final UserService userService;

    public EmailAndPasswordValidator(UserService userService) {
        this.userService = userService;
    }


    public void initialize(EmailAndPassword constraintAnnotation) {
        this.userId = constraintAnnotation.userId();
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
    }

    public boolean validateEmail(Long id, String email) {
        if (id != null) {
            User existingUser = userService.findById(id);
            if (existingUser.getEmail().equals(email)) {
                return true;
            }
        }
        return userService.findByEmail(email) == null;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object id = new BeanWrapperImpl(value).getPropertyValue(userId);
        Object firstFiledValue = new BeanWrapperImpl(value).getPropertyValue(firstField);

        if (firstFiledValue == null) {
            return true;
        }

        if (firstField.equals("password")) {
            Object passwordConfirmation = new BeanWrapperImpl(value).getPropertyValue(secondField);
            return firstFiledValue.equals(passwordConfirmation);
        } else if (firstField.equals("email")) {
            return validateEmail((Long) id, (String) firstFiledValue);
        }
        return false;
    }
}


//    @Override
//    public boolean isValid(Object user, ConstraintValidatorContext constraintValidatorContext) {
//        new BeanWrapperImpl

//        if (user == null) {
//            return true;
//        }
//        Long id = user.getId();
//        String userEmail = user.getEmail();
//
//        User existingUser;
//        if (id == null) {
//            existingUser = userService.findByEmail(userEmail);
//            return existingUser == null;
//        }
//        existingUser = userService.findById(id);
//        String existingUserEmail = existingUser.getEmail();
//        if (existingUserEmail.equals(userEmail)) {
//            return true;
//        }
//        return userService.findByEmail(userEmail) == null;
//    }
//
//    public boolean isValid2(User user, ConstraintValidatorContext constraintValidatorContext) {
//        if (user == null) {
//            return true;
//        }
//        User exisitngUser = userService.findByEmail(user.getEmail());
//        return exisitngUser == null;
//    }

//}
