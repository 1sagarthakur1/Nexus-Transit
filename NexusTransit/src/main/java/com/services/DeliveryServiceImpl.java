package com.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JwtTokenValidatorFilter;
import com.config.OTPGenerator;
import com.config.SandOtp_ByEmail;
import com.exception.LuggageException;
import com.exception.UserException;
import com.exception.VehicleException;
import com.models.MessageDTO;
import com.models.Payment;
import com.models.Transporter_User;
import com.models.VehicleSlote;
import com.repository.LuggageRepo;
import com.repository.PaymentRepo;
import com.repository.Transporter_UserRepo;
import com.repository.UserRepo;
import com.repository.VehicleSloteRepo;

import io.jsonwebtoken.Claims;

@Service
public class DeliveryServiceImpl implements DeliveryService{
	
	@Autowired
	public JwtTokenValidatorFilter jwtTokenValidatorFilter;
	
	@Autowired
	public Transporter_UserRepo transporter_UserRepo;
	
	@Autowired
	public LuggageRepo luggageRepo;
	
	@Autowired
	public VehicleSloteRepo vehicleSloteRepo;
	
	@Autowired
	public PaymentRepo paymentRepo;
	

	@Autowired
	private SandOtp_ByEmail sandOtp_ByEmail;

	@Autowired
	private OTPGenerator otpGenerator;
	
	private String otpset;

	@Override
	public MessageDTO sandOTPfor_Delivery(String token, int vehicleSloteId) throws LuggageException, VehicleException {
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforTransporter_User(token);
		if (claims == null)
			throw new UserException("Please login to view your details");
		Transporter_User transpo_user = transporter_UserRepo.findById(claims.get("transporter_UserID", Integer.class))
				.orElseThrow(() -> new UserException("Please login as Transporter_User"));
		
		Optional<VehicleSlote> vehicleSlote = vehicleSloteRepo.findById(vehicleSloteId);
		
		if(vehicleSlote.isEmpty()) {
			throw new VehicleException("This slote dose not exist");
		}
		
		if(vehicleSlote.get().getVehicle().getTransporter_User().getId() != transpo_user.getId()) {
			throw new LuggageException("This is not your slote");
		}
		
		if(vehicleSlote.get().getAcepten_ornot() == false) {
			throw new LuggageException("This slot is not accepted");
		}
		
		if(vehicleSlote.get().getBooking_ornot() == false) {
			throw new LuggageException("This slot is not booked");
		}
		
		Optional<Payment> pOptional = paymentRepo.findByVehicleSlote(vehicleSlote.get());
		if(pOptional.isEmpty()) {
			throw new LuggageException("Payment not done");
		}
		

		String recipientEmail = vehicleSlote.get().getLuggage().getUser().getEmail();
		String subject = "Dlivery OTP (One Time Password)";
		String otp = otpGenerator.generateOTP();
		this.otpset = otp;
		String body = "If your Luggage is deliverd you can give this otp, Your delivery otp is " + otp;

		String string = sandOtp_ByEmail.sendEmail(recipientEmail, subject, body);
		return new MessageDTO(string, LocalDateTime.now());

	}
	
	@Override
	public MessageDTO varifyOTPfor_Delivery(String token, int vehicleSloteId, String otp) throws LuggageException, VehicleException{
		
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforTransporter_User(token);
		if (claims == null)
			throw new UserException("Please login to view your details");
		Transporter_User transpo_user = transporter_UserRepo.findById(claims.get("transporter_UserID", Integer.class))
				.orElseThrow(() -> new UserException("Please login as Transporter_User"));
		
		Optional<VehicleSlote> vehicleSlote = vehicleSloteRepo.findById(vehicleSloteId);
		
		if(vehicleSlote.isEmpty()) {
			throw new VehicleException("This slote dose not exist");
		}
		if(vehicleSlote.get().getVehicle().getTransporter_User().getId() != transpo_user.getId()) {
			throw new LuggageException("This is not your slote");
		}
		
//		System.out.println(otp);
		
		if (!otp.equals(otpset)) {
			return new MessageDTO("OTP is not valid", LocalDateTime.now());
		}
		
		vehicleSlote.get().setDeliveryOrNot(true);
		VehicleSlote vehicleSlote2 = vehicleSloteRepo.save(vehicleSlote.get());
		if(vehicleSlote == null) {
			return new MessageDTO("Some Thing is worng", LocalDateTime.now());
		}
		
		return new MessageDTO("Luggage Delivered Successfully", LocalDateTime.now());
		
	}

}
