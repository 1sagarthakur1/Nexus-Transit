package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.UserException;
import com.models.MessageDTO;
import com.models.VehicleSlote;
import com.services.VehicleSloteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/NexusTransit")
@CrossOrigin(value = "*")
public class VehicalSloteController {
	
	@Autowired
	private VehicleSloteService vehicleSloteService;
	
	@PostMapping("/VehicleSlote/createVehical_sloteBy_TransporterUser/{vehicalNo}")
	public ResponseEntity<MessageDTO> createVehical_sloteBy_TransporterUser(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("vehicalNo") String vehicalNo,@Valid @RequestBody VehicleSlote vehicleSlote) throws UserException {
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<MessageDTO> responseEntity = new ResponseEntity<>(vehicleSloteService.createVehicleSloteBy_TrasporterUser(jwtToken, vehicalNo, vehicleSlote),HttpStatus.CREATED);
		return responseEntity;
	}
	
	@PostMapping("/VehicleSlote/createVehical_slote_User/{luggageId}/{vehicalNo}")
	public ResponseEntity<MessageDTO> createVehical_slote(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("luggageId") int luggageId ,@PathVariable("vehicalNo") String vehicalNo,@Valid @RequestBody VehicleSlote vehicleSlote) throws UserException {
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<MessageDTO> responseEntity = new ResponseEntity<>(vehicleSloteService.createVehicleSloteBy_User(jwtToken, luggageId,vehicalNo, vehicleSlote),HttpStatus.CREATED);
		return responseEntity;
	}
	
	@PutMapping("/VehicleSlote/book_VehicalSlote/{sloteId}/{luggageId}/{bool}")
	public ResponseEntity<MessageDTO> book_VehicalSlote(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("sloteId") int sloteId,@PathVariable("luggageId") int luggageId, @PathVariable boolean bool) throws UserException {
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<MessageDTO> responseEntity = new ResponseEntity<>(vehicleSloteService.bookedOrCanceldSloteBy_User(jwtToken, sloteId, luggageId, bool),HttpStatus.CREATED);
		return responseEntity;
	}
	
	@PutMapping("/VehicleSlote/accepte_Vehicalslote/{sloteId}/{bool}")
	public ResponseEntity<MessageDTO> accepte_Vehicalslote(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("sloteId") int sloteId, @PathVariable("bool") boolean bool) throws UserException {
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<MessageDTO> responseEntity = new ResponseEntity<>(vehicleSloteService.acceptOrCanceldSloteBy_TrasporterUser(jwtToken, sloteId, bool),HttpStatus.CREATED);
		return responseEntity;
	}
	
	@DeleteMapping("/VehicleSlote/delelteSloteBy_User/{sloteId}")
	public ResponseEntity<MessageDTO> delelteSloteBy_User(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("sloteId") int sloteId) throws UserException {
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<MessageDTO> responseEntity = new ResponseEntity<>(vehicleSloteService.delelteSloteBy_User(jwtToken, sloteId),HttpStatus.CREATED);
		return responseEntity;
	}
	
	@DeleteMapping("/VehicleSlote/delelteSloteBy_TrasporterUser/{sloteId}")
	public ResponseEntity<MessageDTO> delelteSloteBy_TrasporterUser(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("sloteId") int sloteId) throws UserException {
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<MessageDTO> responseEntity = new ResponseEntity<>(vehicleSloteService.delelteSloteBy_TrasporterUser(jwtToken, sloteId),HttpStatus.CREATED);
		return responseEntity;
	}
	
	private String extractJwtToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7);
		}
		return null;
	}
}
