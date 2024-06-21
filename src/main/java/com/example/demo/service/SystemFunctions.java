package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Client;
import com.example.demo.entity.UserLogged;

import come.example.demo.enums.StatusLogging;
import come.example.demo.enums.StatusRegistration;
import come.example.demo.enums.UserRole;

public interface SystemFunctions {
	StatusLogging login(String idClient, String password, UserRole role);

	StatusLogging logoff(String idClient);

	StatusRegistration signup(Client client);

	StatusRegistration signoff(String idClient);

	boolean isUserLoggedOn(String idClient);

	Optional<UserLogged> getUserLoggedOn(String idUser);

	boolean deleteUserLoggedOn(String idUserLogged);

	List<UserLogged> getAllUsersLoggedOn();
}
