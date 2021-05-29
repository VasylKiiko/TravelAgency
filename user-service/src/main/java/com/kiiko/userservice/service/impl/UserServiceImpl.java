package com.kiiko.userservice.service.impl;

import com.kiiko.userservice.dto.UserDto;
import com.kiiko.userservice.exception.UserNotFoundException;
import com.kiiko.userservice.model.User;
import com.kiiko.userservice.repository.UserRepository;
import com.kiiko.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto addUser(UserDto userDto) {
        log.info("Starting adding user {} ...", userDto);
        User userToAdd = mapUserDtoToUser(userDto);
        User addedUser = userRepository.save(userToAdd);
        log.info("User {} was added successfully", addedUser);
        return mapUserToUserDto(addedUser);
    }

    @Override
    public UserDto getUser(String userEmail) {
        log.info("Starting searching user with email {} ...", userEmail);
        User user = userRepository.findUserByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        log.info("User {} was found", user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(String userEmail, UserDto userDto) {
        log.info("Starting updating user with email {}", userEmail);
        User updatedUser = mapUserDtoToUser(userDto);
        User userFromDB = userRepository.findUserByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        updatedUser.setUserId(userFromDB.getUserId());
        User user = userRepository.save(updatedUser);
        log.info("User {} was updated", user);
        return mapUserToUserDto(user);
    }

    @Override
    public void deleteUser(String userEmail) {
        log.info("Starting deleting user with email {}", userEmail);
        User userToDelete = userRepository.findUserByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        userRepository.delete(userToDelete);
        log.info("User {} was deleted", userToDelete);
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .password(userDto.getPassword())
                .userId(userDto.getUserId())
                .build();
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build();
    }
}
