package com.filesharingapp.filesharingapp.services;

import com.filesharingapp.filesharingapp.models.User;

import java.util.List;

public interface IUserService {
    User getUser(Long senderId);
    List<User> getAllUsers();
    User getUserByUsername(String username);
}
