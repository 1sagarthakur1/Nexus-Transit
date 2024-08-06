package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.Vehicle;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer>{
	
	public Optional<Vehicle> findByVehicleNo(String vehicleNo);
	
}
