package com.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JwtTokenValidatorFilter;
import com.exception.UserException;
import com.exception.VehicleException;
import com.models.MessageDTO;
import com.models.Payment;
import com.models.PaymentType;
import com.models.User;
import com.models.VehicleSlote;
import com.repository.PaymentRepo;
import com.repository.UserRepo;
import com.repository.VehicleSloteRepo;

import io.jsonwebtoken.Claims;

@Service
public class PaymetServiceImpl implements PaymentService{
   
	
	@Autowired
	private JwtTokenValidatorFilter jwtTokenValidatorFilter;
	
	@Autowired
	private VehicleSloteRepo vehicleSloteRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@Override
	public MessageDTO payment(String token, int vehicleSloteId, Payment payment) throws UserException, VehicleException {
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforUser(token);
		if (claims == null)
			throw new UserException("Please login to view your details");
		User user = userRepo.findById(claims.get("userId", Integer.class))
				.orElseThrow(() -> new UserException("Please login as User"));
		
		
		Optional<VehicleSlote> vOptional = vehicleSloteRepo.findById(vehicleSloteId);
		if(vOptional.isEmpty()) {
			throw new VehicleException("This Slote is not present");
		}
		
		if(vOptional.get().getLuggage() == null || vOptional.get().getLuggage().getUser().getUserID() != user.getUserID()) {
			throw new VehicleException("This is not your slote. give your slote id");
		}
		
		if(vOptional.get().getBooking_ornot() == false) {
			throw new VehicleException("first you have to book this vehicle slote");
		}
		
		if(vOptional.get().getAcepten_ornot() == false) {
			throw new VehicleException("The transporter has not accepted it yet, payment cannot be made now");
		}
		
		payment.setPayment(vOptional.get().getDistance_KM()*vOptional.get().getPrice_perKM());
		payment.setLocalDateTime(LocalDateTime.now());
		payment.setVehicleSlote(vOptional.get());
		
		
		if(payment.getPaymentType() == PaymentType.AFTER_DELIVERY) {
			payment.setUpiIdString(null);
			payment.setUpiPin(null);
			payment.setPaymentType(PaymentType.AFTER_DELIVERY);
		}else {
			payment.setPaymentType(PaymentType.PAY_NOW);
		}
		
		if(payment.getPaymentType() == PaymentType.PAY_NOW && (payment.getUpiIdString() == null || payment.getUpiIdString() == null)) {
			throw new VehicleException("Payment details is require");
		}
		
		
		Payment payment2 = paymentRepo.save(payment);
		
		if(payment2 == null) {
			return new MessageDTO("Payment faild..", LocalDateTime.now());
		}
		
		return new MessageDTO("Payment done successfully", LocalDateTime.now());
	}

}
