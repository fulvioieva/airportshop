package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	String[] valoriNonAmmessi = {"1","2","3","4","5","6","7","8","9",".","-","_"};

	@Override
	public boolean addUser(User user) {
		List<String> listMail = new ArrayList<>();
		List<User> listUsers = userRepository.findAll();
		for (User user2 : listUsers) {
			listMail.add(user2.getMail());
		}
		if (listMail.contains(user.getMail())) {
			return false;
		}
		for (String client2 : user.getName().trim().split("")) {
			if (Arrays.asList(valoriNonAmmessi).contains(client2)) {
				return false;
			}
		}
		for (String client2 : user.getSurname().trim().split("")) {
			if (Arrays.asList(valoriNonAmmessi).contains(client2)) {
				return false;
			}
		}
		if (user.getPassword().toString().length()<8 | user.getPassword().toString().length()>15) {
			return false;
		}
		userRepository.save(user);
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		if (userRepository.findById(user.getId()).isEmpty()) {
			return false;
		}
		for (String client2 : user.getName().trim().split("")) {
			if (Arrays.asList(valoriNonAmmessi).contains(client2)) {
				return false;
			}
		}
		for (String client2 : user.getSurname().trim().split("")) {
			if (Arrays.asList(valoriNonAmmessi).contains(client2)) {
				return false;
			}
		}
		if (user.getPassword().toString().length()<8 | user.getPassword().toString().length()>15) {
			return false;
		}
		userRepository.save(user);
		return true;
	}

	@Override
	public List<User> getAllUser() {
		List<User> listUser = userRepository.findAll();
		if (listUser.isEmpty()) {
			throw new NullPointerException();
		}
		return listUser;
	}

	@Override
	public Optional<User> getUser(int idUser) {
		return userRepository.findById(idUser);
	}

	@Override
	public boolean deleteUser(int idUser) {
		if (userRepository.findById(idUser).isEmpty()) {
			return false;
		}
		userRepository.deleteById(idUser);
		return true;
	}
	
	
}
