package com.mr.Airport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mr.Airport.entity.Ticket;
import com.mr.Airport.entity.User;
import com.mr.Airport.interfaces.UserFunctions;
import com.mr.Airport.repository.UserRepository;

@Service
public class UserService implements UserFunctions {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<Ticket> getTicketsByUserId(long userId) throws Exception {
		if (!userRepository.existsById(userId)) { throw new Exception(); }
		User user = userRepository.findById(userId).get();
		return user.getTickets();
	}
	
	@Override
	public boolean ifExistUser(long userId) {
		return userRepository.existsById(userId);
	}
	
	@Override
	public boolean ifUserIsLogged(long userId) {
		if (!ifExistUser(userId)) { return false; }
		int userStatus = userRepository.findById(userId).get().getLogged();
		if (userStatus == 0) { return false; }
		return true;
	}

	@Override
	public boolean login(String clientCode, String password) {
		// Controllo se il clientCode esiste
		Optional<User> user = userRepository.findByClientCode(clientCode);
		if (user.isEmpty()) { return false; }
		
		// Controllo se esiste la password associata a quel codice cliente.
		if (!user.get().getPassword().equals(password)) { return false; }
		
		// Loggo l'utente
		userRepository.updateUserLoginById(user.get().getId());
		return true;
	}

	@Override
	public boolean logout(String clientCode) throws Exception {
		// Controllo se il clientCode esiste
		Optional<User> user = userRepository.findByClientCode(clientCode);
		if (user.isEmpty()) { throw new Exception(); }
		
		// Se è già sloggato torno false
		if (user.get().getLogged() == 0) { return false; }
		
		// Sloggo l'utente
		userRepository.updateUserLogoutById(user.get().getId());
		return true;
	}

	@Transactional // Questo garantisce che entrambe le operazioni (save dell'utente e generazione cod. cliente) vengano eseguite come un'unica unità di lavoro. (Atomicità)
	@Override
	public boolean signin(String name, String surname, String email, String password) throws Exception {
		
		// Controllo se la mail è già esistente
		if (userRepository.findByEmail(email).isPresent()) { return false; }
		
		// Creo un nuovo oggetto user
		User newUser = new User();
		newUser.setName(name);
		newUser.setSurname(surname);
		newUser.setEmail(email);
		newUser.setPassword(password);
		userRepository.save(newUser);
		long newUserId = newUser.getId();
		
		// Genero il codice cliente
		this.generateClientCode(newUserId);
		return true;
	}

	@Override
	public boolean signout(String clientCode) throws Exception {
		// Controllo se il clientCode esiste
		Optional<User> user = userRepository.findByClientCode(clientCode);
		if (user.isEmpty()) { throw new Exception(); }
		
		userRepository.delete(user.get());
		return true;
	}

	@Override
	public Optional<User> getUserById(long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public Optional<User> getUserByClientCode(String clientCode) {
		return userRepository.findByClientCode(clientCode);
	}
	
	/* Method Name: generateClientCode
	 * Method Arguments: User user
	 * Method Return: boolean or Exception
	 * Method Errors: Exception
	 * 
	 * Questo metodo genera un codice cliente, utilizzando l'id dell'utente.
	 * Questo metodo va utilizzato dopo aver già inserito l'utente nel database,
	 * altrimenti l'id non esisterebbe.
	 * Il formato del codice cliente è formato dalla lettera C seguita da
	 * tanti 0 + id finale per un totale di 9 cifre. */
	@Override
	public boolean generateClientCode(long userId) throws Exception {
		// FORMAT:  C + %000 + id   ->   C000000001
	
		if (!this.ifExistUser(userId)) { throw new Exception(); }
		String clientCode = String.format("C%09d", userId);
		userRepository.updateClientCodeById(clientCode, userId);
		return true;
	}

}
