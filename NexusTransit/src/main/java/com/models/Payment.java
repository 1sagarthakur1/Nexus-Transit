package com.models;

import java.time.LocalDateTime;

import org.apache.tomcat.util.net.openssl.ciphers.MessageDigest;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Payment {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @NotNull(message = "Payment type is required")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    
    private String upiIdString;

    @Pattern(regexp = "\\d{4,6}", message = "UPI PIN must be 4 to 6 digits")
    private String upiPin;
	
    @NotNull(message = "Payment date and time is required")
    @PastOrPresent(message = "Payment date and time must be in the past or present")
    private LocalDateTime localDateTime;
	
    @OneToOne
    private VehicleSlote vehicleSlote;
	
    @Min(value = 0, message = "Payment must be positive")
    private int payment;

}
