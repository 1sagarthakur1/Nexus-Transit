package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.TokenException;
import com.exception.UserException;
import com.models.LoginDTO;
import com.models.TokenGiverDTO;
import com.services.Transporter_UserService;
import com.services.UserService;

@RestController
@RequestMapping("/api/NexusTransit")
@CrossOrigin(value = "*")
public class LoginController {

	@Autowired
	private Transporter_UserService transporter_UserService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/User/login")
	public ResponseEntity<TokenGiverDTO> userLoginHandler(@RequestBody LoginDTO dto) throws UserException, TokenException{
		TokenGiverDTO tokenGiverDTO = userService.loginUser(dto);
		return new ResponseEntity<>(tokenGiverDTO, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/Transporter_User/login")
	public ResponseEntity<TokenGiverDTO> transporter_UserLoginHandler(@RequestBody LoginDTO dto) throws UserException, TokenException{
		TokenGiverDTO tokenGiverDTO = transporter_UserService.loginTransporter_User(dto);
		return new ResponseEntity<>(tokenGiverDTO, HttpStatus.ACCEPTED);
	}
	
	
	
	
	
}
