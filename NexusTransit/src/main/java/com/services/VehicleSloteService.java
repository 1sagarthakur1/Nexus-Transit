package com.services;

import com.exception.LuggageException;
import com.exception.UserException;
import com.exception.VehicleException;
import com.models.MessageDTO;
import com.models.VehicleSlote;

public interface VehicleSloteService {
	
	public MessageDTO createVehicleSloteBy_TrasporterUser(String token, String vehicalNo, VehicleSlote vehicleSlote) throws UserException, VehicleException, LuggageException;
	
	public MessageDTO createVehicleSloteBy_User(String token, int laggageId, String vehicalNo, VehicleSlote vehicleSlote) throws UserException, VehicleException, LuggageException;
	
	public MessageDTO bookedOrCanceldSloteBy_User(String token, int sloteId, int luggageId, boolean bool)throws UserException, VehicleException;
	
	public MessageDTO acceptOrCanceldSloteBy_TrasporterUser(String token, int sloteId, boolean bool)throws UserException, VehicleException;
	
	public MessageDTO delelteSloteBy_User(String token, int sloteId) throws UserException, VehicleException;
	
	public MessageDTO delelteSloteBy_TrasporterUser(String token, int sloteId) throws UserException, VehicleException;
}
