package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Client;
import com.example.demo.entity.UserLogged;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.UserRepository;

import come.example.demo.enums.StatusLogging;
import come.example.demo.enums.StatusRegistration;
import come.example.demo.enums.UserRole;

@Service
public class ServiceSystem implements SystemFunctions {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Override
	@Transactional
	public StatusLogging login(String idClient, String password, UserRole role) {
		if (userRepository.existsById(idClient)) {
			return StatusLogging.ALREADY_LOGGED_ON;
		}

		Optional<Client> c = clientRepository.findById(idClient);
		UserLogged log = new UserLogged();

		if (c.isPresent()) {
			if (!c.get().getDtSignoff().isBlank()) {
				return StatusLogging.ALREADY_SIGN_OFF;
			}
			if (c.get().getPassword().equals(password)) {
				log.setIdUser(idClient);
				log.setTmLogin(LocalTime.now().toString().substring(0, 8));
				log.setDtLogin(LocalDate.now().toString());
				log.setUserRole(role);
				userRepository.save(log);
				c.get().setDtLastLogin(log.getDtLogin());
				c.get().setTmLastLogin(log.getTmLogin());
				clientRepository.save(c.get());
				return StatusLogging.LOGIN_SUCCESFULLY;
			} else {
				return StatusLogging.WRONG_PASSWORD;
			}

		}
		return StatusLogging.WRONG_ID_CLIENT;
	}

	@Override
	public StatusLogging logoff(String idClient) {
		Optional<UserLogged> u = userRepository.findById(idClient);
		if (clientRepository.findById(idClient).isEmpty()) {
			return StatusLogging.WRONG_ID_CLIENT;
		}
		if (!clientRepository.findById(idClient).get().getDtSignoff().isBlank()) {
			return StatusLogging.ALREADY_SIGN_OFF;
		}
		if (u.isPresent()) {
			userRepository.delete(u.get());
			return StatusLogging.LOGIN_OFF_SUCCESFULLY;
		}
		return StatusLogging.ALREADY_LOGGED_OF;
	}

	@Override
	@Transactional
	public StatusRegistration signup(Client client) {
		Optional<Client> c = clientRepository.findById(client.getIdClient());
		if (c.isPresent()) {
			if (c.get().getDtSignoff().isBlank()) {
				return StatusRegistration.ALREADY_SIGNED_UP;
			}
		}
		List<Client> cL = clientRepository.findBymail(client.getMail());
		if (cL.size() > 0) {
			if (cL.get(cL.size() - 1).getDtSignoff().isBlank()) {
				return StatusRegistration.MAIL_ALREADY_EXISTS;
			}
		}
		if (!ServiceSystem.isValidEmailAddress(client.getMail())) {
			return StatusRegistration.WRONG_MAIL;
		}
		if (client.getPassword().isEmpty()) {
			return StatusRegistration.WRONG_PASSWORD;
		}
		client.setDtSignup(LocalDate.now().toString());
		client.setDtLastLogin("");
		client.setDtSignoff("");
		client.setTmLastLogin("");
		clientRepository.save(client);
		return StatusRegistration.SIGNED_UP_SUCCESFULLY;
	}

	public static boolean isValidEmailAddress(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}

		int atIndex = email.indexOf('@');
		int dotIndex = email.lastIndexOf('.');
		if (atIndex <= 0 || dotIndex <= atIndex || dotIndex == email.length() - 1) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public StatusRegistration signoff(String idClient) {
		Optional<Client> c = clientRepository.findById(idClient);
		if (c.isEmpty()) {
			return StatusRegistration.SIGNED_OFF_UNSUCCESFULLY;
		}
		if (!c.get().getDtSignoff().isEmpty()) {
			return StatusRegistration.SIGNED_OFF_UNSUCCESFULLY;
		}
		if (userRepository.existsById(c.get().getIdClient())) {
			userRepository.deleteById(c.get().getIdClient());
		}
		c.get().setDtSignoff(LocalDate.now().toString());
		clientRepository.save(c.get());
		return StatusRegistration.SIGNED_OFF_SUCCESFULLY;
	}

	@Override
	public boolean isUserLoggedOn(String idClient) {
		return userRepository.existsById(idClient);
	}

	@Override
	public Optional<UserLogged> getUserLoggedOn(String idUser) {
		return userRepository.findById(idUser);
	}

	@Override
	public boolean deleteUserLoggedOn(String idUserLogged) {
		if (userRepository.existsById(idUserLogged)) {
			userRepository.deleteById(idUserLogged);
			return true;
		}
		return false;
	}

	@Override
	public List<UserLogged> getAllUsersLoggedOn() {
		return userRepository.findAll();
	}

}
