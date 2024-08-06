package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.UserException;
import com.models.MessageDTO;
import com.models.User;
import com.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/NexusTransit")
@CrossOrigin(value = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/User/forTestApplication")
	public ResponseEntity<String> forTestApplication(@RequestHeader("Authorization") String authorizationHeader) throws UserException{
		ResponseEntity<String> userResponseEntity = new ResponseEntity<>(authorizationHeader,
				HttpStatus.CREATED);
		return userResponseEntity;
	}
	
	@PostMapping("/User/create_account")
	public ResponseEntity<String> sandOptFor_addUser(@Valid @RequestBody User user) throws UserException{
		ResponseEntity<String> userResponseEntity = new ResponseEntity<>(userService.sandOptFor_addUser(user),
				HttpStatus.CREATED);
		return userResponseEntity;
	}

	@PostMapping("/User/verifyOtp/{otp}")
	public ResponseEntity<MessageDTO> addUser(@PathVariable String otp) throws UserException {
		ResponseEntity<MessageDTO> userResponseEntity = new ResponseEntity<>(userService.createUser(otp),
				HttpStatus.CREATED);
		return userResponseEntity;
	}
	
	@GetMapping("/User/current_User")
	public ResponseEntity<User> currentUser(@RequestHeader("Authorization") String authorizationHeader) throws UserException {
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<User> userResponseEntity = new ResponseEntity<>(userService.currentUser(jwtToken),
				HttpStatus.CREATED);
		return userResponseEntity;
	}
	
	
	private String extractJwtToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7);
		}
		return null;
	}
}
