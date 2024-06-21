package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Client;

public interface AdministrationFunctions {
	boolean addClient(Client client);

	Optional<Client> getClient(String idClient);

	boolean deleteClient(String idClient);

	boolean updateClient(Client client);

	List<Client> getAllClient();

	List<Client> getAllClient(String dtSignupStart, String dtSignupEnd);

	List<Client> getAllClient(String surnameLike);
}
