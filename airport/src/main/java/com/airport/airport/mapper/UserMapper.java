package com.airport.airport.mapper;

import java.util.stream.Collectors;

import com.airport.airport.dto.UserDTO;
import com.airport.airport.model.User;

public class UserMapper {

	public static UserDTO convertToUserDTO(User user) {
		
		UserDTO dto = new UserDTO();
		
		dto.setIdUser(user.getIdUser());
		dto.setName(user.getName());
		dto.setSurname(user.getSurname());
		dto.setPassword(user.getPassword());
		dto.setTickets(user.getTickets().stream()
				.map(TicketMapper::convertToTicketDTO)
				.collect(Collectors.toList()));
		
		return dto;
		
	}
	
}
