package pl.coderslab.validator.impl;

import pl.coderslab.repository.UserRepository;
import pl.coderslab.validator.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private final UserRepository userRepository;

    public PasswordValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(Password constraintAnnotation) {
    }

    public static boolean findMatch(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(userRepository.findAll());
        if (value == null) {
            return true;
        }
        if (!findMatch("\\p{Lu}", value)) {
            return false;
        }
        if (!findMatch("\\p{Ll}", value)) {
            return false;
        }
        if (!findMatch("[!@#$%^&*()_\\-+={}\\[\\]|:;'`\"<,>\\\\.?/]", value)) {
            return false;
        }
        return findMatch("\\d", value);
    }
}
