package com.mr.Airport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mr.Airport.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	// Query SQL per trovare l'user tramite clientCode
	Optional<User> findByClientCode(String clientCode);
	
	// Query SQL per trovare l'user tramite email
	Optional<User> findByEmail(String email);
	
	// Query JPQL per aggiornare la colonna del "clientCode" con il codice generato.
	// Il ritorno int indica il numero di righe aggiornate. Si può utilizzare per verificare l'avvenuta modifica.
	@Modifying // Indica che questa query modifica i dati nel database (insert, update, delete).
    @Transactional // Richiede che il metodo sia eseguito all'interno di una transazione. È necessario per le operazioni di modifica.
    @Query("UPDATE User u SET u.clientCode = :clientCode WHERE u.id = :userId") // Specifica la query JPQL per eseguire l'aggiornamento
    int updateClientCodeById(@Param("clientCode") String clientCode, @Param("userId") Long userId);
	
	// Query JPQL per aggiornare lo stato di login dell'utente.
	@Modifying
    @Transactional
    @Query("UPDATE User u SET u.logged = 1 WHERE u.id = :userId")
	int updateUserLoginById(@Param("userId") Long userId);
	
	// Query JPQL per aggiornare lo stato di logout dell'utente.
	@Modifying
    @Transactional
    @Query("UPDATE User u SET u.logged = 0 WHERE u.id = :userId")
	int updateUserLogoutById(@Param("userId") Long userId);
}