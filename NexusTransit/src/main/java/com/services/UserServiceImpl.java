package com.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.models.User;
import com.repository.UserRepo;

import io.jsonwebtoken.Claims;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JwtTokenGeneratorFilter jwtTokenGeneratorFilter;

	@Autowired
	private JwtTokenValidatorFilter jwtTokenValidatorFilter;

	@Autowired
	private PasswordConverter passwordConverter;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private SandOtp_ByEmail sandOtp_ByEmail;

	@Autowired
	private OTPGenerator otpGenerator;

	private User userset;

	private String otpString;

	@Override
	public String sandOptFor_addUser(User user) throws UserException {
		// TODO Auto-generated method stub

		Optional<User> user2 = userRepo.findByEmail(user.getEmail());
		if (!user2.isEmpty()) {
			throw new UserException("User Allready Exist");
		}

		String recipientEmail = user.getEmail();
		String subject = "Register OTP (One Time Password)";
		String otp = otpGenerator.generateOTP();
		this.otpString = otp;
		String body = "Welcome to NexusTransit, Your ragister otp is " + otp;
		this.userset = user;

		String string = sandOtp_ByEmail.sendEmail(recipientEmail, subject, body);
		return string;
	}

	@Override
	public MessageDTO createUser(String otp) throws UserException {
		// TODO Auto-generated method stub

		if (!otp.equals(otpString)) {
			return new MessageDTO("OTP is not valid", LocalDateTime.now());
		}

		Optional<User> user2 = userRepo.findByEmail(userset.getEmail());
		if (!user2.isEmpty()) {
			throw new UserException("User Allready Exist");
		}
		userset.setPassword(passwordConverter.hashPassword(userset.getPassword()));
		User user3 = userRepo.save(userset);
		if (user3 != null) {
			return new MessageDTO("User Created Successfully", LocalDateTime.now());
		}
		return new MessageDTO("User not Created somthing is worng", LocalDateTime.now());
	}

	@Override
	public TokenGiverDTO loginUser(LoginDTO loginDTO) throws UserException, TokenException {
		Optional<User> user = userRepo.findByEmail(loginDTO.getEmail());

		if (user.isEmpty())
			throw new UserException("Please enter a valid email");

		if (!passwordConverter.verifyPassword(loginDTO.getPassword(), user.get().getPassword()))
			throw new UserException("Invalid Password...");

		String token = jwtTokenGeneratorFilter.tokenGerneratorForUser(user.get());

		TokenGiverDTO tokenGiverDTO = new TokenGiverDTO(token, LocalDateTime.now());

		return tokenGiverDTO;
	}

	@Override
	public User currentUser(String token) throws UserException, TokenException {
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforUser(token);
		if (claims == null)
			throw new UserException("Please login to view your details");
		User user = userRepo.findById(claims.get("userId", Integer.class))
				.orElseThrow(() -> new UserException("Please login as User"));

		return user;
	}

}
