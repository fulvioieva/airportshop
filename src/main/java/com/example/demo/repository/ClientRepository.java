package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Client;

public interface ClientRepository extends CrudRepository<Client, String> {
	List<Client> findAll();

	List<Client> findBymail(String mail);	
	

}
