package com.mr.Airport.interfaces;

import java.util.List;
import java.util.Optional;

import com.mr.Airport.entity.Ticket;
import com.mr.Airport.entity.User;

public interface UserFunctions {
	/* 
	 * getTicketsByUserId -
	 * login -
	 * logout -
	 * getAllUser
	 * getUserById -
	 * getLoggedUser
	 * getClientCodeFromUserId
	 * getUserByClientCode -
	 * signin -
	 * signout -
	 * updateUser
	 * generateClientCode -
	 * existUser - */
	
	List<Ticket> getTicketsByUserId(Long userId) throws Exception;
	boolean ifExistUser(Long userId);
	boolean login(String clientCode, String password);
	boolean logout(String clientCode);
	boolean signin(String name, String surname, String email, String password) throws Exception;
	boolean signout(String clientCode);
	Optional<User> getUserById(Long userId);
	Optional<User> getUserByClientCode(String clientCode);
	boolean generateClientCode(Long userId) throws Exception;
}
