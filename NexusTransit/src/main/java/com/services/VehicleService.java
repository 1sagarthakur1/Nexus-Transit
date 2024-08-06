package com.services;

import java.util.List;

import com.exception.UserException;
import com.exception.VehicleException;
import com.models.MessageDTO;
import com.models.Vehicle;

public interface VehicleService {
	
	public MessageDTO addVehicle(String token, Vehicle vehicle) throws VehicleException, UserException;
	
	public List<Vehicle> getAllVehicles(String token) throws VehicleException, UserException;
}
