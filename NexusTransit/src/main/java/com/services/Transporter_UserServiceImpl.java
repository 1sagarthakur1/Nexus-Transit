package com.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JwtTokenGeneratorFilter;
import com.config.JwtTokenValidatorFilter;
import com.config.OTPGenerator;
import com.config.PasswordConverter;
import com.config.SandOtp_ByEmail;
import com.exception.TokenException;
import com.exception.UserException;
import com.models.LoginDTO;
import com.models.MessageDTO;
import com.models.TokenGiverDTO;
import com.models.Transporter_User;
import com.models.User;
import com.repository.Transporter_UserRepo;

import io.jsonwebtoken.Claims;

@Service
public class Transporter_UserServiceImpl implements Transporter_UserService{
	
	@Autowired
	private JwtTokenGeneratorFilter jwtTokenGeneratorFilter;
	
	@Autowired
	private JwtTokenValidatorFilter jwtTokenValidatorFilter;
	
	@Autowired
	private PasswordConverter passwordConverter;
	
	@Autowired
	private Transporter_UserRepo transpo_userRepo;
	
	@Autowired
	private SandOtp_ByEmail sandOtp_ByEmail;

	@Autowired
	private OTPGenerator otpGenerator;

	private Transporter_User transporter_UserSet;

	private String otpString;

	
	@Override
	public String sandOptFor_addTransporter_User(Transporter_User transporter_User) throws UserException {
		
		Optional<Transporter_User> user2 = transpo_userRepo.findByEmail(transporter_User.getEmail());
		if (!user2.isEmpty()) {
			throw new UserException("Transporter_User Allready Exist");
		}

		String recipientEmail = transporter_User.getEmail();
		String subject = "Register OTP (One Time Password) for Transporter User";
		String otp = otpGenerator.generateOTP();
		this.otpString = otp;
		String body = "Welcome to NexusTransit, Your ragister otp is " + otp;
		this.transporter_UserSet = transporter_User;

		String string = sandOtp_ByEmail.sendEmail(recipientEmail, subject, body);
		return string;
	}

	@Override
	public MessageDTO createTransporter_User(String otp) throws UserException {
		// TODO Auto-generated method stub
		if (!otp.equals(otpString)) {
			return new MessageDTO("OTP is not valid", LocalDateTime.now());
		}
		
		Optional<Transporter_User> transpo_user2 = transpo_userRepo.findByEmail(transporter_UserSet.getEmail());
		if(!transpo_user2.isEmpty()) {
			throw new UserException("Transporter_User Allready Exist");
		}
		transporter_UserSet.setPassword(passwordConverter.hashPassword(transporter_UserSet.getPassword()));
		Transporter_User transpo_user3 = transpo_userRepo.save(transporter_UserSet);
		if(transpo_user3 != null) {
			return new MessageDTO("Transporter_User Created Successfully", LocalDateTime.now());
		}
		return new MessageDTO("Transporter_User not Created somthing is worng", LocalDateTime.now());
	}

	@Override
	public TokenGiverDTO loginTransporter_User(LoginDTO loginDTO) throws UserException, TokenException {
		Optional<Transporter_User> transpo_user = transpo_userRepo.findByEmail(loginDTO.getEmail());

		if (transpo_user.isEmpty())
			throw new UserException("Please enter a valid email");

		if (!passwordConverter.verifyPassword(loginDTO.getPassword(), transpo_user.get().getPassword()))
			throw new UserException("Invalid Password...");

		String token = jwtTokenGeneratorFilter.tokenGerneratorForTransporter_User(transpo_user.get());

		TokenGiverDTO tokenGiverDTO = new TokenGiverDTO(token, LocalDateTime.now());

		return tokenGiverDTO;
	}

	@Override
	public Transporter_User currentTransporter_User(String token) throws UserException, TokenException {
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforTransporter_User(token);
		if (claims == null)
			throw new UserException("Please login to view your details");
		Transporter_User transpo_user = transpo_userRepo.findById(claims.get("transporter_UserID", Integer.class))
				.orElseThrow(() -> new UserException("Please login as Transporter_User"));

		return transpo_user;
	}

}
