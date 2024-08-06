package com.services;

import com.exception.LoginException;
import com.exception.TokenException;
import com.exception.UserException;
import com.models.Luggage;
import com.models.MessageDTO;

public interface LuggageService {
	
	public MessageDTO addLuggage(String token, Luggage luggage) throws LoginException, UserException, TokenException;
}
