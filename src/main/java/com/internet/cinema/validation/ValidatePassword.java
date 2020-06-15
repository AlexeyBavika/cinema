package com.internet.cinema.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatePassword {
    String message() default "Password and repeated password should be the same.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
