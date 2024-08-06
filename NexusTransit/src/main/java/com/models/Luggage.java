package com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Luggage {
	
	    @Id
	    @JsonProperty(access = Access.READ_ONLY)
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "luggageGenerator")
	    @SequenceGenerator(name = "luggageGenerator", sequenceName = "luggageGen", allocationSize = 1, initialValue = 1)
	    private int id;

	    @NotBlank(message = "Luggage name is required")
	    @Size(max = 100, message = "Luggage name cannot be longer than 100 characters")
	    private String luggageName;

	    @NotBlank(message = "Type is required")
	    @Size(max = 50, message = "Type cannot be longer than 50 characters")
	    private String type;

	    @Size(max = 255, message = "Description cannot be longer than 255 characters")
	    private String description;

	    private String image;

	    @Min(value = 0, message = "Weight must be a positive number")
	    private int weight;

	    @NotNull(message = "Pick-up address is required")
	    @Embedded
	    private Pickup_Address pickUpAddress;

	    @NotNull(message = "Drop address is required")
	    @Embedded
	    private Drop_Address dropAddress;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    @JsonIgnore
	    private User user;
	
}
