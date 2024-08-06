package com.services;

import com.exception.UserException;
import com.models.MessageDTO;
import com.models.Payment;

public interface PaymentService {
	public MessageDTO payment(String token,int vehicleSloteId,  Payment payment) throws UserException;
}
