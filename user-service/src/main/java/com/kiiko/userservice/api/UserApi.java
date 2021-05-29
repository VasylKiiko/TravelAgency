package com.kiiko.userservice.api;

import com.kiiko.userservice.controller.model.UserModel;
import com.kiiko.userservice.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Api(tags = "User management API")
@RequestMapping(path = "user-service/api/v1/users")
@Validated
public interface UserApi {
    @ApiOperation("Adding user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Validated(UserDto.Registration.class)
    UserModel addUser(@Valid @RequestBody UserDto userDto);

    @ApiOperation("Getting user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userEmail", paramType = "path", value = "User's email", required = true, dataType = "String")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userEmail}")
    UserModel getUser(@Email @PathVariable(name = "userEmail") String userEmail);

    @ApiOperation("Updating information about user with email")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userEmail", paramType = "path", value = "User's email", required = true, dataType = "String")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userEmail}")
    @Validated(UserDto.Updating.class)
    UserModel updateUser(@Email @PathVariable String userEmail, @Valid @RequestBody UserDto userDto);

    @ApiOperation("Deleting user with email")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userEmail", paramType = "path", value = "User's email", required = true, dataType = "String")
    })
    @DeleteMapping("/{userEmail}")
    ResponseEntity<Void> deleteUser(@Email @PathVariable String userEmail);
}
