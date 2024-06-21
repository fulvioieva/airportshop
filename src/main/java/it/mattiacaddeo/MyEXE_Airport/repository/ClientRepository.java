package it.mattiacaddeo.MyEXE_Airport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.mattiacaddeo.MyEXE_Airport.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>{

	Optional<Client> findByClientCode(String username);

}
