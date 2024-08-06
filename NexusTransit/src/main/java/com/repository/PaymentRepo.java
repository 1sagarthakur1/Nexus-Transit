package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.Payment;
import com.models.VehicleSlote;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer>{
	
	public Optional<Payment> findByVehicleSlote(VehicleSlote vehicleSlote);
}
