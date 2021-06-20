package com.upc.finances.domain.service;

import com.upc.finances.domain.model.User;
import com.upc.finances.resource.UserSignInResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
    User getUserById(Long userId);
    User register(User user);
    User signIn(UserSignInResource resource);
}
