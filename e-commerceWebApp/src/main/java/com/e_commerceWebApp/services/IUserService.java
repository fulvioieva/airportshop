package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    boolean existsById(int userId);

    User getUserById(int userId);
    
}
