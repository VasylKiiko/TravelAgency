package com.kiiko.userservice.controller;

import com.kiiko.userservice.api.UserApi;
import com.kiiko.userservice.controller.assembler.UserAssembler;
import com.kiiko.userservice.controller.model.UserModel;
import com.kiiko.userservice.dto.UserDto;
import com.kiiko.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;
    private final UserAssembler userAssembler;

    @Override
    public UserModel addUser(UserDto userDto) {
        log.info("User to add {}", userDto);
        UserDto addedUser = userService.addUser(userDto);
        log.info("User is added {}", addedUser);
        Link selfLink = linkTo(methodOn(UserController.class).addUser(userDto)).withSelfRel();
        UserModel userModel = userAssembler.toModel(addedUser);
        userModel.add(selfLink);
        return userModel;
    }

    @Override
    public UserModel getUser(String userEmail) {
        log.info("Starting finding user with email {}", userEmail);
        UserDto foundUser = userService.getUser(userEmail);
        log.info("User is found {}", foundUser);

        Link selfLink = linkTo(methodOn(UserController.class).getUser(userEmail)).withSelfRel();
        UserModel userModel = userAssembler.toModel(foundUser);
        userModel.add(selfLink);
        return userModel;
    }

    @Override
    public UserModel updateUser(String userEmail, UserDto userDto) {
        log.info("User to update {}", userDto);
        UserDto updatedUser = userService.updateUser(userEmail, userDto);
        log.info("Updated user {}", updatedUser);

        Link selfLink = linkTo(methodOn(UserController.class).updateUser(userEmail, userDto)).withSelfRel();
        UserModel userModel = userAssembler.toModel(updatedUser);
        userModel.add(selfLink);
        return userModel;
    }

    @Override
    public ResponseEntity<Void> deleteUser(String userEmail) {
        log.info("User to be deleted {}", userEmail);
        userService.deleteUser(userEmail);
        log.info("User was deleted");

        return ResponseEntity.noContent().build();
    }
}
