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
import com.models.MessageDTO;
import com.models.Vehicle;
import com.services.VehicleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/NexusTransit")
@CrossOrigin(value = "*")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("/Vehicle/addVehicle")
	public ResponseEntity<MessageDTO> addVehicle(@RequestHeader("Authorization") String authorizationHeader,@Valid @RequestBody Vehicle vehicle) throws UserException {
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<MessageDTO> responseEntity = new ResponseEntity<>(vehicleService.addVehicle(jwtToken, vehicle),HttpStatus.CREATED);
		return responseEntity;
	}
	
	private String extractJwtToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7);
		}
		return null;
	}
}
