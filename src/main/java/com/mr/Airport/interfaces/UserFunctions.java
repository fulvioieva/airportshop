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
	
	List<Ticket> getTicketsByUserId(long userId) throws Exception;
	boolean ifExistUser(long userId);
	boolean ifUserIsLogged(long userId);
	boolean login(String clientCode, String password);
	boolean logout(String clientCode) throws Exception;
	boolean signin(String name, String surname, String email, String password) throws Exception;
	boolean signout(String clientCode) throws Exception;
	Optional<User> getUserById(long userId);
	Optional<User> getUserByClientCode(String clientCode);
	boolean generateClientCode(long userId) throws Exception;
}
