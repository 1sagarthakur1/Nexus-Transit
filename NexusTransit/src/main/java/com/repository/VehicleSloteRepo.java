package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.VehicleSlote;

@Repository
public interface VehicleSloteRepo extends JpaRepository<VehicleSlote, Integer>{
	
	Optional<VehicleSlote> findBySloteCreaterAndLuggageIdAndVehicleId(String sloteCreater, int luggageId, int vehicleId);

	Optional<VehicleSlote> findBySloteCreaterAndVehicleId(String sloteCreater, int vehicleId);

	
}
