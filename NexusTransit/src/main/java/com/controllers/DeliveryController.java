package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.UserException;
import com.models.MessageDTO;
import com.services.DeliveryService;

@RestController
@RequestMapping("/api/NexusTransit")
@CrossOrigin(value = "*")
public class DeliveryController {
	
	@Autowired
	private DeliveryService deliveryService;
	
	@PostMapping("/User/sandOTPfor_Delivery/{vehicleSlotId}")
	public ResponseEntity<MessageDTO> sandOTPfor_Delivery(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("vehicleSlotId") int vehicleSlotId) throws UserException{
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<MessageDTO> userResponseEntity = new ResponseEntity<>(deliveryService.sandOTPfor_Delivery(jwtToken, vehicleSlotId),
				HttpStatus.CREATED);
		return userResponseEntity;
	}
	
	@PutMapping("/User/varifyOTPfor_Delivery/{vehicleSlotId}/{otp}")
	public ResponseEntity<MessageDTO> varifyOTPfor_Delivery(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("vehicleSlotId") int vehicleSlotId, @PathVariable("otp") String otp) throws UserException{
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<MessageDTO> userResponseEntity = new ResponseEntity<>(deliveryService.varifyOTPfor_Delivery(jwtToken, vehicleSlotId, otp),
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
