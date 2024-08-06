package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.User;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	public Optional<User> findByMobileNumber(String mobileNumber);
	
	public Optional<User> findByEmail(String email);	
}
