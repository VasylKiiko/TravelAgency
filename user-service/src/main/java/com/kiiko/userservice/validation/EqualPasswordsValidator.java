package com.kiiko.userservice.validation;

import com.kiiko.userservice.dto.UserDto;
import com.kiiko.userservice.validation.annotation.EqualPasswords;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, UserDto> {
    @Override
    public void initialize(EqualPasswords constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDto user, ConstraintValidatorContext context) {
        if (user.getPassword() == null || user.getRepeatPassword() == null) {
            context.buildConstraintViolationWithTemplate("Password can't be empty")
                    .addConstraintViolation();
            return false;
        }
        return user.getPassword().equals(user.getRepeatPassword());
    }
}
