package pl.coderslab.validator;

import pl.coderslab.validator.impl.EmailAlreadyExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailAlreadyExistsValidator.class)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAlreadyExists {
    String message() default "Adres email już istnieje.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
