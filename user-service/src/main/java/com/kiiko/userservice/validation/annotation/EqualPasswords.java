package com.kiiko.userservice.validation.annotation;

import com.kiiko.userservice.validation.EqualPasswordsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EqualPasswordsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EqualPasswords {
    String message() default "Password and repeat password should be equal";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
