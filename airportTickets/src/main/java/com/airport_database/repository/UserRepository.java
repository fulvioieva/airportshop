package com.airport_database.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airport_database.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
