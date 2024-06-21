package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Cliente;

@Repository
public interface ClientRepository extends CrudRepository<Cliente, Integer>{

}
