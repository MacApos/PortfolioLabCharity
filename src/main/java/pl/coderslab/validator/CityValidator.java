package pl.coderslab.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityValidator implements ConstraintValidator<Street, String> {
    @Override
    public void initialize(Street constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        Pattern compiledPattern = Pattern.compile("\\p{Lu}\\p{Ll}+((( )|-)\\p{Lu}\\p{Ll}+)*");
        Matcher matcher = compiledPattern.matcher(value.replaceAll(" ", ""));
        return matcher.matches();
    }
}
