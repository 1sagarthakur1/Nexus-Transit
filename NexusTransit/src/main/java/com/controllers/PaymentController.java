package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.models.MessageDTO;
import com.models.Payment;
import com.services.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/NexusTransit")
@CrossOrigin(value = "*")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("Payment/doPayment/{sloteId}")
	public ResponseEntity<MessageDTO> doPayment(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("sloteId") Integer sloteId,@Valid @RequestBody Payment payment){
		String jwtToken = extractJwtToken(authorizationHeader);
		return new ResponseEntity<MessageDTO>(paymentService.payment(jwtToken,sloteId, payment), HttpStatus.OK);
	}
	
	private String extractJwtToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7);
		}
		return null;
	}
}
