package com.kiiko.userservice.exception;

import com.kiiko.userservice.model.ErrorType;

public class UserNotFoundException extends ServiceException{

    private static final String DEFAULT_MASSAGE = "User not found ";

    public UserNotFoundException() {
        super(DEFAULT_MASSAGE);
    }

    public UserNotFoundException(String message) {
        super(DEFAULT_MASSAGE + message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR;
    }
}
