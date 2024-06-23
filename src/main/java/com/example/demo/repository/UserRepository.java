package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByDateBirthBetween(Date date_birth, Date date_birth2);
	List<User> findByAddressLike(String addressLike);
}
