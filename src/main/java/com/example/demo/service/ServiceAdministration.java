package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;

@Service
public class ServiceAdministration implements AdministrationFunctions {
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public boolean addClient(Client client) {
		return !clientRepository.existsById(client.getIdClient()) && client != null
				? clientRepository.save(client) != null
				: false;
	}

	@Override
	public Optional<Client> getClient(String idClient) {
		return clientRepository.findById(idClient);
	}

	@Override
	public boolean deleteClient(String idClient) {
		if (clientRepository.existsById(idClient)) {
			clientRepository.deleteById(idClient);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateClient(Client client) {
		return clientRepository.existsById(client.getIdClient()) ? clientRepository.save(client) != null : false;
	}

	@Override
	public List<Client> getAllClient() {
		List<Client> list = new ArrayList<>();
		clientRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public List<Client> getAllClient(String dtSignupStart, String dtSignupEnd) {
		List<Client> list = new ArrayList<>();
		clientRepository.findAll().forEach(e -> {
			if (!e.getDtSignup().isBlank() && !e.getDtSignoff().isBlank()) {
				if (LocalDate.parse(e.getDtSignup()).isAfter(LocalDate.parse(dtSignupStart))
						&& LocalDate.parse(e.getDtSignoff()).isBefore(LocalDate.parse(dtSignupEnd))) {
					list.add(e);
				}
			}

		});
		return list;
	}

	@Override
	public List<Client> getAllClient(String surnameLike) {
		List<Client> list = new ArrayList<>();
		clientRepository.findAll().forEach(e -> {
			if (e.getSurname().equals(surnameLike)) {
				list.add(e);
			}
		});
		return list;
	}

}
