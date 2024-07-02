package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.User;
import com.e_commerceWebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public boolean existsById(int userId) {
        return userRepo.existsById(userId);
    }

    @Override
    public User getUserById(int userId) {
        return userRepo.findById(userId).get();
    }

}
