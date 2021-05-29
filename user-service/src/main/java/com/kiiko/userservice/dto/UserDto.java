package com.kiiko.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kiiko.userservice.validation.annotation.EqualPasswords;
import com.kiiko.userservice.validation.annotation.UniqueEmail;
import com.kiiko.userservice.validation.annotation.ValidPassword;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
@EqualPasswords(groups = {UserDto.Registration.class})
public class UserDto {
    public interface Registration {}
    public interface Updating {}

    private Long userId;

    @Email(message = "Invalid email!")
    @UniqueEmail
    @NotNull(groups = {Registration.class, Updating.class})
    private String email;

    @ValidPassword
    @NotNull(groups = {Registration.class, Updating.class})
    private String password;
    @NotNull(groups = {Registration.class})
    private String repeatPassword;

    @NotNull(groups = {Registration.class, Updating.class})
    private String firstname;
    @NotNull(groups = {Registration.class, Updating.class})
    private String lastname;
}
