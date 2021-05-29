package com.kiiko.userservice.validation;

import com.kiiko.userservice.validation.annotation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator for password.
 * Password should match the requirements:
 * - Has size between 8 and 20.
 * - Contains at least 1 uppercase symbol
 * - Contains at least 1 lowercase symbol
 * - Contains at least 1 not character symbol
 */
public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private static final String EMPTY_PASSWORD = "Password can't be empty!";

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.equals("")) {
            context.buildConstraintViolationWithTemplate(EMPTY_PASSWORD).addConstraintViolation();
            return false;
        }
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
