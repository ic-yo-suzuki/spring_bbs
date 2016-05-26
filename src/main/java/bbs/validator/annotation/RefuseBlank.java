package bbs.validator.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import bbs.validator.RefuseBlankValidator;

@Documented
@Constraint(validatedBy = RefuseBlankValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface RefuseBlank {
    String message() default "Please type in the fields.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
