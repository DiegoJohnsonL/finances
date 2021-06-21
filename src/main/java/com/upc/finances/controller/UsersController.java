package com.upc.finances.controller;

import com.upc.finances.domain.model.User;
import com.upc.finances.domain.service.UserService;
import com.upc.finances.resource.SaveUserResource;
import com.upc.finances.resource.UserResource;
import com.upc.finances.resource.UserSignInResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/users")
    public Page<UserResource> getAllPlayers(Pageable pageable){
        List<UserResource> users = userService.getAllUsers(pageable).stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(users, pageable, users.size());
    }
    @GetMapping("/users/{id}")
    public UserResource getUserById(@PathVariable(name = "id") Long userId){
        return convertToResource(userService.getUserById(userId));
    }
    @PostMapping("/users/signup")
    public UserResource Signup(@Valid @RequestBody SaveUserResource resource){
        return convertToResource(userService.register(convertToEntity(resource)));
    }
    @PostMapping("/users/signin")
    public UserResource SignIn(@Valid @RequestBody UserSignInResource resource){
        return convertToResource(userService.signIn(resource));
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
