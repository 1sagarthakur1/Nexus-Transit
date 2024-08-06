package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.UserException;
import com.models.Luggage;
import com.models.MessageDTO;
import com.services.LuggageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/NexusTransit")
@CrossOrigin(value = "*")
public class LuggageController {
	
	@Autowired
	private LuggageService luggageService;
	
	@PostMapping("/Luggage/addLuggage")
	public ResponseEntity<MessageDTO> addLuggage(@RequestHeader("Authorization") String authorizationHeader,@Valid @RequestBody Luggage luggage) throws UserException {
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<MessageDTO> laggagResponseEntity = new ResponseEntity<>(luggageService.addLuggage(jwtToken, luggage),HttpStatus.CREATED);
		return laggagResponseEntity;
	}
	
	private String extractJwtToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7);
		}
		return null;
	}
}
