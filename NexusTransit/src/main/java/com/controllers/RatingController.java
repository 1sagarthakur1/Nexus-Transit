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
import com.models.Rating;
import com.services.RatingService;

@RestController
@RequestMapping("/api/NexusTransit")
@CrossOrigin(value = "*")
public class RatingController {
	
	@Autowired
	public RatingService ratingService;
	
	@PostMapping("/Rating/giveRating/{vehicalid}")
	public ResponseEntity<MessageDTO> giveRating(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Rating rating, @PathVariable("vehicalid") Integer vehicalId ) throws UserException {
		String jwtToken = extractJwtToken(authorizationHeader);
		ResponseEntity<MessageDTO> responseEntity = new ResponseEntity<>(ratingService.giveRating(jwtToken, rating, vehicalId),
				HttpStatus.CREATED);
		return responseEntity;
	}
	
	private String extractJwtToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7);
		}
		return null;
	}
}
