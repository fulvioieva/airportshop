package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;

public interface IUserService {
	boolean   addUser(User user);
	boolean   updateUser (User user);
	List<User> getAllUser();
	Optional<User> getUser(int idUser);
	boolean deleteUser(int idUser);
}
