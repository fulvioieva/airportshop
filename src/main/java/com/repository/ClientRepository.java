package com.repository;


import org.springframework.data.repository.CrudRepository;

import com.entity.Client;


public interface ClientRepository extends CrudRepository<Client, String> {

}
