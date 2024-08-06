package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.models.Luggage;

@Repository
public interface LuggageRepo extends JpaRepository<Luggage, Integer>{
	
	 @Query("SELECT l FROM Luggage l WHERE l.id = :luggageId AND l.user.id = :userId")
	 Optional<Luggage> findByLuggageIdAndUserId(@Param("luggageId") int luggageId, @Param("userId") int userId);

}
