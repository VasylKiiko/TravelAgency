package com.kiiko.userservice.service;

import com.kiiko.userservice.dto.UserDto;

public interface UserService {
    UserDto addUser(UserDto userDto);
    UserDto getUser(String userEmail);
    UserDto updateUser(String userEmail, UserDto userDto);
    void deleteUser(String userEmail);
}
