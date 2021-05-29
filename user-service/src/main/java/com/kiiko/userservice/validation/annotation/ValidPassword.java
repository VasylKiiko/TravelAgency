package com.kiiko.userservice.validation.annotation;

import com.kiiko.userservice.validation.ValidPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ValidPassword{
    String message() default "Password should match the requirements!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
