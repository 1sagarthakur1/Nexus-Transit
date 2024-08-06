package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.Transporter_User;

@Repository
public interface Transporter_UserRepo extends JpaRepository<Transporter_User, Integer>{
	
//	public Optional<Transporter_User>  findByMoNumber(String moNumber);
	
	public Optional<Transporter_User>  findByEmail(String email);
}
