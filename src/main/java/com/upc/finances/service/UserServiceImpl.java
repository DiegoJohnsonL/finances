package com.upc.finances.service;

import com.upc.finances.domain.model.User;
import com.upc.finances.domain.repository.UserRepository;
import com.upc.finances.domain.service.UserService;
import com.upc.finances.exception.ResourceNotFoundException;
import com.upc.finances.resource.UserSignInResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new ResourceNotFoundException("User already exists with email" + user.getEmail());

        return userRepository.save(user);
    }

    @Override
    public User signIn(UserSignInResource resource) {
        return userRepository.findByEmailAndPassword(resource.getEmail(), resource.getPassword())
                .orElseThrow(()-> new ResourceNotFoundException("Wrong email or password"));
    }
}
