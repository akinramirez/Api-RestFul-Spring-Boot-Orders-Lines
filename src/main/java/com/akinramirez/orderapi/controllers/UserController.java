package com.akinramirez.orderapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akinramirez.orderapi.converters.UserConverter;
import com.akinramirez.orderapi.dtos.LoginRequestDTO;
import com.akinramirez.orderapi.dtos.LoginResponseDTO;
import com.akinramirez.orderapi.dtos.SignupRequestDTO;
import com.akinramirez.orderapi.dtos.UserDTO;
import com.akinramirez.orderapi.entity.User;
import com.akinramirez.orderapi.services.UserService;
import com.akinramirez.orderapi.utils.WrapperResponse;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserConverter userConverter;

	@PostMapping(value="/signup")
	public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignupRequestDTO request){
		User user = userService.createUser(userConverter.signup(request));
		return new WrapperResponse<>(true, "success", userConverter.fromEntity(user))
				.createResponse();
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request){
		LoginResponseDTO response =  userService.login(request);
		return new WrapperResponse<>(true, "success", response).createResponse();
	}

}
