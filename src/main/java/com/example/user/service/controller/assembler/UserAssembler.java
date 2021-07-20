package com.example.user.service.controller.assembler;

import com.example.user.service.controller.UserController;
import com.example.user.service.controller.model.UserModel;
import com.example.user.service.dto.UserDto;
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

        Link get = linkTo(methodOn(UserController.class).getUser(entity.getEmail())).withRel("get");
        Link delete = linkTo(methodOn(UserController.class).deleteUser(entity.getEmail())).withRel("delete");

        userModel.add(get, delete);
        return userModel;
    }
}
