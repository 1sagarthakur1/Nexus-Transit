package com.services;

import com.exception.TokenException;
import com.exception.UserException;
import com.models.LoginDTO;
import com.models.MessageDTO;
import com.models.TokenGiverDTO;
import com.models.Transporter_User;
import com.models.User;

public interface Transporter_UserService {
	
	public String sandOptFor_addTransporter_User(Transporter_User transporter_User)throws UserException;
	
	public MessageDTO createTransporter_User(String string) throws UserException;

	public TokenGiverDTO loginTransporter_User(LoginDTO loginDTO) throws UserException, TokenException;

	public Transporter_User currentTransporter_User(String token) throws UserException, TokenException;
}
