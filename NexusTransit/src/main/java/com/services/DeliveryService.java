package com.services;

import com.exception.LuggageException;
import com.exception.VehicleException;
import com.models.MessageDTO;

public interface DeliveryService {
	
	public MessageDTO sandOTPfor_Delivery(String token, int luggagesId) throws LuggageException;
	
	public MessageDTO varifyOTPfor_Delivery(String token, int vehicleSloteId, String otp) throws LuggageException, VehicleException;
	
}
