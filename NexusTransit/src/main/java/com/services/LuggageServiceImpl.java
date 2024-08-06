package com.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JwtTokenValidatorFilter;
import com.exception.LuggageException;
import com.exception.LoginException;
import com.exception.TokenException;
import com.exception.UserException;
import com.models.Luggage;
import com.models.MessageDTO;
import com.models.User;
import com.repository.LuggageRepo;
import com.repository.UserRepo;

import io.jsonwebtoken.Claims;

@Service
public class LuggageServiceImpl implements LuggageService{
	
	@Autowired
	private JwtTokenValidatorFilter jwtTokenValidatorFilter;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private LuggageRepo luggageRepo;

	@Override
	public MessageDTO addLuggage(String token, Luggage luggage) throws UserException, TokenException {
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforUser(token);
		if (claims == null)
			throw new LoginException("Please login to view your details");
		User user = userRepo.findById(claims.get("userId", Integer.class))
				.orElseThrow(() -> new UserException("Please login as User"));
		
		if(luggage == null) {
			throw new LuggageException("Please Enter valid details");
		}
		
		luggage.setUser(user);
		
		Luggage luggage2 = luggageRepo.save(luggage);
		
		if(luggage2 == null) {
			return new MessageDTO("Luggage has not been added", LocalDateTime.now());
		}
		
		return new MessageDTO("Luggage has been added successfully", LocalDateTime.now());

	}

	


	

}
