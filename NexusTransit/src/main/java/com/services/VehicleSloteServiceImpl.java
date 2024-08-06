package com.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JwtTokenValidatorFilter;
import com.exception.LuggageException;
import com.exception.UserException;
import com.exception.VehicleException;
import com.models.Luggage;
import com.models.MessageDTO;
import com.models.Payment;
import com.models.PaymentType;
import com.models.Transporter_User;
import com.models.User;
import com.models.Vehicle;
import com.models.VehicleSlote;
import com.repository.LuggageRepo;
import com.repository.PaymentRepo;
import com.repository.Transporter_UserRepo;
import com.repository.UserRepo;
import com.repository.VehicleRepo;
import com.repository.VehicleSloteRepo;

import io.jsonwebtoken.Claims;

@Service
public class VehicleSloteServiceImpl implements VehicleSloteService{
	
	@Autowired
	private JwtTokenValidatorFilter jwtTokenValidatorFilter;
	
	@Autowired
	private Transporter_UserRepo transporter_UserRepo;
	
	@Autowired
	private VehicleRepo vehicleRepo;
	
	@Autowired
	private VehicleSloteRepo vehicleSloteRepo;
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private LuggageRepo luggageRepo;

	@Override
	public MessageDTO createVehicleSloteBy_TrasporterUser(String token, String vehicalNo, VehicleSlote vehicleSlote)
			throws UserException, VehicleException, LuggageException {
		
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforTransporter_User(token);
		if (claims == null)
			throw new UserException("Please login to view your details");
		Transporter_User transpo_user = transporter_UserRepo.findById(claims.get("transporter_UserID", Integer.class))
				.orElseThrow(() -> new UserException("Please login as Transporter_User"));
		
		if(vehicleSlote == null) {	
			throw new VehicleException("fill all details for Vehicle Slote");	
		}
		
		Optional<Vehicle> optional = vehicleRepo.findByVehicleNo(vehicalNo);
		
		if(optional.isEmpty()) {
			throw new  VehicleException("The transporter does not have this vehicle");
		}
		
		vehicleSlote.setSloteCreater("Transporter_user");
		vehicleSlote.setBooking_ornot(false);
		vehicleSlote.setAcepten_ornot(false);
		vehicleSlote.setVehicle(optional.get());
		vehicleSlote.setDeliveryOrNot(false);
		
		VehicleSlote vehicleSlote2 = vehicleSloteRepo.save(vehicleSlote);
		
		if(vehicleSlote2 == null) {
			throw new VehicleException("Vehicle slote have not been created");
		}
		
		return new MessageDTO("Vehicle slote have been created Successfully",LocalDateTime.now());
	}

	@Override
	public MessageDTO createVehicleSloteBy_User(String token, int luggageId, String vehicalNo, VehicleSlote vehicleSlote)
			throws UserException, VehicleException, LuggageException {
		// TODO Auto-generated method stub
		
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforUser(token);
		if (claims == null)
			throw new UserException("Please login to view your details");
		User user = userRepo.findById(claims.get("userId", Integer.class))
				.orElseThrow(() -> new UserException("Please login as User"));
		
		if(vehicleSlote == null) {	
			throw new VehicleException("fill all details for Vehicle Slote");	
		}
		
		Optional<Vehicle> optional = vehicleRepo.findByVehicleNo(vehicalNo);
		if(optional.isEmpty()) {
			throw new  VehicleException("The transporter does not have this vehicle");
		}
		
		Optional<Luggage> luggageOptional = luggageRepo.findByLuggageIdAndUserId(luggageId, user.getUserID());
		if(luggageOptional.isEmpty()) {
			throw new LuggageException("User dose not have this luggage");
		}
		
		vehicleSlote.setSloteCreater("User");
		vehicleSlote.setBooking_ornot(true);
		vehicleSlote.setAcepten_ornot(false);
		vehicleSlote.setVehicle(optional.get());
		vehicleSlote.setLuggage(luggageOptional.get());
		vehicleSlote.setDeliveryOrNot(false);
		
		VehicleSlote vehicleSlote2 = vehicleSloteRepo.save(vehicleSlote);
		
		if(vehicleSlote2 == null) {
			throw new VehicleException("Vehicle slote have not been created");
		}
		
		return new MessageDTO("Vehicle slote have been created Successfully",LocalDateTime.now());
		
	}

	@Override
	public MessageDTO bookedOrCanceldSloteBy_User(String token, int sloteId, int luggageId, boolean bool) throws UserException, VehicleException {
		// TODO Auto-generated method stub
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforUser(token);
	    if (claims == null) {
	        throw new UserException("Please login to view your details");
	    }
	    
	    User user = userRepo.findById(claims.get("userId", Integer.class))
	            .orElseThrow(() -> new UserException("Please login as User"));
	   
	    
		Optional<VehicleSlote> vehicleSloteOptional = vehicleSloteRepo.findById(sloteId);
		if(vehicleSloteOptional == null) {
			throw new VehicleException("Vehicle slote not exist");
		}
		
		if(vehicleSloteOptional.get().getSloteCreater().equals("User")) {
			throw new VehicleException("You can not book this slote because this is user slote");
		}
		
		Optional<VehicleSlote> vOptional =  vehicleSloteRepo.findBySloteCreaterAndLuggageIdAndVehicleId("Transporter_user", luggageId, vehicleSloteOptional.get().getVehicle().getId());
		
		if(vOptional.isPresent() && bool == true) {
			throw new VehicleException("You can book only one transporter user vehicle slote");
		}
		
		Optional<Payment> pOptional = paymentRepo.findByVehicleSlote(vehicleSloteOptional.get());
	
		if(!pOptional.isEmpty() && pOptional.get().getPaymentType() == PaymentType.PAY_NOW && bool == false) {
			throw new VehicleException("Payment has been done, you can not canceld it");
		}
		
		Luggage luggage;
		
		try {
			Optional<Luggage> luggageOptional = luggageRepo.findByLuggageIdAndUserId(luggageId, user.getUserID());
			luggage = luggageOptional.get();
		} catch (Exception e) {
			throw new LuggageException("This luggage not exist");
		}		
		
		if(bool == true) {
			
			vehicleSloteOptional.get().setLuggage(luggage);
			vehicleSloteOptional.get().setBooking_ornot(bool);
			
		}else {
		
			vehicleSloteOptional.get().setLuggage(null);
			vehicleSloteOptional.get().setBooking_ornot(bool);
			
		}
		
		VehicleSlote vehicleSlote = vehicleSloteRepo.save(vehicleSloteOptional.get());
		
		if(vehicleSlote == null) {
			throw new VehicleException("Vehicle slote have not been booked");
		}
		
		return new MessageDTO( bool ? "Vehicle slote have been booked" : "Vehicle booked slote canceld",LocalDateTime.now());
		
	}

	@Override
	public MessageDTO acceptOrCanceldSloteBy_TrasporterUser(String token, int sloteId, boolean bool) throws UserException, VehicleException {
		
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforTransporter_User(token);
	    if (claims == null) {
	        throw new UserException("Please login to view your details");
	    }
	    Transporter_User transporter_User = transporter_UserRepo.findById(claims.get("transporter_UserID", Integer.class))
	            .orElseThrow(() -> new UserException("Please login as User"));
	    
	    
	    VehicleSlote vehicleSloteOptional;
		
		try {
			Optional<VehicleSlote> vOptional = vehicleSloteRepo.findById(sloteId);
			vehicleSloteOptional = vOptional.get();
					
		} catch (Exception e) {
			throw new LuggageException("This slot not exist");
		}	
		
		if(vehicleSloteOptional == null) {
			throw new VehicleException("Vehicle slote not exist");
		}
		
		Optional<Payment> pOptional = paymentRepo.findByVehicleSlote(vehicleSloteOptional);
		
		if(!pOptional.isEmpty() && pOptional.get().getPaymentType() == PaymentType.PAY_NOW && bool == false) {
			throw new VehicleException("Payment has been done, you can not canceld it");
		}
		
//		if(vehicleSloteOptional.getAcepten_ornot() == true) {
//			throw new VehicleException("You slote all ready accepted");
//		}

		
		vehicleSloteOptional.setAcepten_ornot(bool);
		
		VehicleSlote vehicleSlote = vehicleSloteRepo.save(vehicleSloteOptional);
		
		if(vehicleSlote == null) {
			throw new VehicleException("Vehicle slote have not been affeted");
		}
		
		return new MessageDTO(bool ? "Vehicle slote have been accepted" : "canceld acceptation",LocalDateTime.now());
	}

	@Override
	public MessageDTO delelteSloteBy_User(String token, int sloteId) throws UserException, VehicleException {
		
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforUser(token);
	    if (claims == null) {
	        throw new UserException("Please login to view your details");
	    }
	    
	    User user = userRepo.findById(claims.get("userId", Integer.class))
	            .orElseThrow(() -> new UserException("Please login as User"));
	    
	    
        VehicleSlote vehicleSloteOptional;
        
		try {
			Optional<VehicleSlote> vOptional = vehicleSloteRepo.findById(sloteId);
			vehicleSloteOptional = vOptional.get();
					
		} catch (Exception e) {
			throw new LuggageException("This slot not exist");
		}
		
		if(vehicleSloteOptional.getSloteCreater().equals("Transporter_user")) {
			throw new VehicleException("You can not delete this slote");
		};
	    
		if(user.getUserID() != vehicleSloteOptional.getLuggage().getUser().getUserID()) {
			throw new VehicleException("This is not your slote you can not delete it");
		}
		
		
		vehicleSloteRepo.delete(vehicleSloteOptional);
		
		return new MessageDTO("Vehicle slote have been deleted",LocalDateTime.now());
	}

	@Override
	public MessageDTO delelteSloteBy_TrasporterUser(String token, int sloteId) throws UserException, VehicleException {
		// TODO Auto-generated method stub
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforTransporter_User(token);
	    if (claims == null) {
	        throw new UserException("Please login to view your details");
	    }
	    Transporter_User transporter_User = transporter_UserRepo.findById(claims.get("transporter_UserID", Integer.class))
	            .orElseThrow(() -> new UserException("Please login as User"));
	    
        VehicleSlote vehicleSloteOptional;
        
		try {
			Optional<VehicleSlote> vOptional = vehicleSloteRepo.findById(sloteId);
			vehicleSloteOptional = vOptional.get();
					
		} catch (Exception e) {
			throw new LuggageException("This slot not exist");
		}
		
		if(transporter_User.getId() != vehicleSloteOptional.getVehicle().getTransporter_User().getId()) {
			throw new VehicleException("This is not your slote you can not delete it");
		}
		
		vehicleSloteRepo.delete(vehicleSloteOptional);
		
		return new MessageDTO("Vehicle slote have been deleted",LocalDateTime.now());
	}

}
