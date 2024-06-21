package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.UserLogged;

public interface UserRepository extends CrudRepository<UserLogged, String> {
	List<UserLogged> findAll();
}
