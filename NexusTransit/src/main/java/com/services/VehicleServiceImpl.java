package com.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JwtTokenValidatorFilter;
import com.exception.UserException;
import com.exception.VehicleException;
import com.models.MessageDTO;
import com.models.Transporter_User;
import com.models.Vehicle;
import com.repository.Transporter_UserRepo;
import com.repository.VehicleRepo;

import io.jsonwebtoken.Claims;

@Service
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private JwtTokenValidatorFilter jwtTokenValidatorFilter;
	
	@Autowired
	private VehicleRepo vehicleRepo;
	
	@Autowired
	private Transporter_UserRepo transporter_UserRepo;

	@Override
	public MessageDTO addVehicle(String token, Vehicle vehicle) throws VehicleException, UserException {
		// TODO Auto-generated method stub
		
		Claims claims = jwtTokenValidatorFilter.tokenValidatingforTransporter_User(token);
		if (claims == null)
			throw new UserException("Please login to view your details");
		Transporter_User transpo_user = transporter_UserRepo.findById(claims.get("transporter_UserID", Integer.class))
				.orElseThrow(() -> new UserException("Please login as Transporter_User"));
		
		Optional<Vehicle> optional = vehicleRepo.findByVehicleNo(vehicle.getVehicleNo());
		
		if(!optional.isEmpty()) {
			throw new VehicleException("This Vehicle allready present");
		}
		
		vehicle.setTransporter_User(transpo_user);
		Vehicle vehicle2 = vehicleRepo.save(vehicle);
		
		if(vehicle2 == null) {
			return new MessageDTO("Vehicle have not been added try again",LocalDateTime.now());
		}
				
		return new MessageDTO("Vehicle have been added successfully",LocalDateTime.now());
	}

	@Override
	public List<Vehicle> getAllVehicles(String token) throws VehicleException, UserException {
		
		return null;
	}

}
