package com.upc.finances.controller;

import com.upc.finances.domain.model.User;
import com.upc.finances.domain.service.UserService;
import com.upc.finances.resource.UserSignInResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Page<User> getAllPlayers(Pageable pageable){
        List<User> users = userService.getAllUsers(pageable).toList();
        return new PageImpl<>(users, pageable, users.size());
    }
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(name = "id") Long userId){
        return userService.getUserById(userId);
    }
    @PostMapping("/users/signup")
    public User Signup(@Valid @RequestBody User user){
        return userService.register(user);
    }
    @PostMapping("/users/signin")
    public User SignIn(@Valid @RequestBody UserSignInResource resource){
        return userService.signIn(resource);
    }
}
