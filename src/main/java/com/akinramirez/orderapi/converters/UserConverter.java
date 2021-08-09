package com.akinramirez.orderapi.converters;

import com.akinramirez.orderapi.dtos.SignupRequestDTO;
import com.akinramirez.orderapi.dtos.UserDTO;
import com.akinramirez.orderapi.entity.User;

public class UserConverter extends AbstractConverter<User, UserDTO>{

	@Override
	public UserDTO fromEntity(User entity) {
		if(entity == null) return null;
		return UserDTO.builder()
				.id(entity.getId())
				.username(entity.getUsername())
				.build();
	}

	@Override
	public User fromDTO(UserDTO dto) {
		if(dto == null) return null;
		return User.builder()
				.id(dto.getId())
				.username(dto.getUsername())
				.build();
	}

	public User signup(SignupRequestDTO dto) {
		if(dto == null) return null;
		return User.builder()
				.username(dto.getUsername())
				.password(dto.getPassword())
				.build();
	}
	
}
