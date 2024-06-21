package com.airport_database.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport_database.entity.User;
import com.airport_database.repository.UserRepository;

@Service
public class UserServices implements IUserServices{
	
	@Autowired UserRepository userRepo;

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public boolean existsById(String userId) {
		return userRepo.existsById(userId);
	}

	@Override
	public User getUserById(String userId) {
		return userRepo.findById(userId).get();
	}

}
