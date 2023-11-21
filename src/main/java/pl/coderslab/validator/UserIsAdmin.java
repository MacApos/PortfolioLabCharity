package pl.coderslab.validator;

import pl.coderslab.validator.impl.PasswordConfirmedValidator;
import pl.coderslab.validator.impl.UserIsAdminValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserIsAdminValidator.class)
@Target({ElementType.TYPE_USE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserIsAdmin {
    String message() default "Użytkownik jest już zarejestrowany jako administrator.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
