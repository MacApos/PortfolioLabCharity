package pl.coderslab.validator.impl;

import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EmailNotFound;
import pl.coderslab.validator.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@SessionAttributes("token")
public class TokenValidator implements ConstraintValidator<Token, String> {
    private final HttpSession session;

    public TokenValidator(HttpSession session) {
        this.session = session;
    }

    @Override
    public void initialize(Token constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        String token = (String)session.getAttribute("token");
        return value.equals(token);
    }
}
