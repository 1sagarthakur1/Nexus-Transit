package com.services;

import java.util.List;

import com.models.MessageDTO;
import com.models.Rating;

public interface RatingService {
	public MessageDTO giveRating(String token, Rating rating,int vehicleId);
}

