package com.kiiko.userservice.controller.assembler;

import com.kiiko.userservice.controller.UserController;
import com.kiiko.userservice.controller.model.UserModel;
import com.kiiko.userservice.dto.UserDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDto, UserModel> {

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserDto entity) {
        UserModel userModel = new UserModel(entity);
        Link getLink = linkTo(methodOn(UserController.class).getUser(entity.getEmail())).withRel("get");
        Link addLink = linkTo(methodOn(UserController.class).addUser(entity)).withRel("add");
        Link updateLink = linkTo(methodOn(UserController.class).updateUser(entity.getEmail(), entity)).withRel("update");
        Link deleteLink = linkTo(methodOn(UserController.class).deleteUser(entity.getEmail())).withRel("delete");
        userModel.add(getLink, addLink, updateLink, deleteLink);
        return userModel;
    }
}
