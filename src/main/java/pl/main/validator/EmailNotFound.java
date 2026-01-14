package pl.main.validator;

import pl.main.validator.impl.EmailNotFoundValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailNotFoundValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailNotFound {
    String message() default "Adres email nie istnieje.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
