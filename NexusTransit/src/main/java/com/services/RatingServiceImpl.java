package com.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JwtTokenValidatorFilter;
import com.exception.UserException;
import com.exception.VehicleException;
import com.models.MessageDTO;
import com.models.Rating;
import com.models.User;
import com.models.Vehicle;
import com.repository.RatingRepo;
import com.repository.UserRepo;
import com.repository.VehicleRepo;

import io.jsonwebtoken.Claims;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	public JwtTokenValidatorFilter jwtTokenValidatorFilter;
	
	@Autowired
	public UserRepo userRepo;
	
	@Autowired
	public VehicleRepo vehicleRepo;
	
	@Autowired
	public RatingRepo ratingRepo;

	@Override
	public MessageDTO giveRating(String token, Rating rating, int vehicleId) {
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforUser(token);
		if (claims == null)
			throw new UserException("Please login to view your details");
		User user = userRepo.findById(claims.get("userId", Integer.class))
				.orElseThrow(() -> new UserException("Please login as User"));
		
		Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);
		
		if(vehicle.isEmpty()) {
			throw new VehicleException("This vehicle not available");
		}
		
		if(rating == null) {
			throw new VehicleException("Add rating details");
		}
		
		rating.setUsers(user);
		rating.setVehicle(vehicle.get());
		
		Rating rating2 = ratingRepo.save(rating);
		if(rating2 == null) {
			return new MessageDTO("Rating have not been added", LocalDateTime.now());
		}
		return new MessageDTO("Rating have been added successfuly", LocalDateTime.now());
	}

}
