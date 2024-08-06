package com.services;

import com.exception.TokenException;
import com.exception.UserException;
import com.models.LoginDTO;
import com.models.MessageDTO;
import com.models.TokenGiverDTO;
import com.models.User;

public interface UserService {
	
	public String sandOptFor_addUser(User user)throws UserException;
	
	public MessageDTO createUser(String otp) throws UserException;
	
	public TokenGiverDTO loginUser(LoginDTO loginDTO) throws UserException, TokenException;
	
	public User currentUser(String token) throws UserException, TokenException;
	
}
