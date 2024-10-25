package com.filesharingapp.filesharingapp.services;

import com.filesharingapp.filesharingapp.models.User;
import com.filesharingapp.filesharingapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }

        return user.get();
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return user.get();

    }
}
