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
import com.models.Transporter_User;
import com.services.Transporter_UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/NexusTransit")
@CrossOrigin(value = "*")
public class Transporter_UserController {
	
	@Autowired
	private Transporter_UserService transporter_UserService;

	@PostMapping("/Transporter_User/create_account")
	public ResponseEntity<String> sandOptFor_addTransporter_User(@Valid @RequestBody Transporter_User transporter_User) throws UserException {
		ResponseEntity<String> transporter_UserResponseEntity = new ResponseEntity<>(transporter_UserService.sandOptFor_addTransporter_User(transporter_User), HttpStatus.CREATED);
		return transporter_UserResponseEntity;
	}
	
	@PostMapping("/Transporter_User/verifyOtp/{otp}")
	public ResponseEntity<MessageDTO> addTransporter_User(@PathVariable String otp) throws UserException {
		ResponseEntity<MessageDTO> transporter_UserResponseEntity = new ResponseEntity<>(transporter_UserService.createTransporter_User(otp),
				HttpStatus.CREATED);
		return transporter_UserResponseEntity;
	}
	
	@GetMapping("/Transporter_User/current_Transporter_User")
	public ResponseEntity<Transporter_User> currentTransporter_User(@RequestHeader("Authorization") String authorizationHeader) throws UserException {
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<Transporter_User> transporter_UserResponseEntity = new ResponseEntity<>(transporter_UserService.currentTransporter_User(jwtToken),
				HttpStatus.CREATED);
		return transporter_UserResponseEntity;
	}
	
	private String extractJwtToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7);
		}
		return null;
	}
}
