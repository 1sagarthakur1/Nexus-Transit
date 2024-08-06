package com.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
public class Transporter_User {
	
	@Id
	@JsonProperty(access = Access.READ_ONLY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
	@SequenceGenerator(name = "userGenerator", sequenceName = "userGen", allocationSize = 1, initialValue = 1)
	private int id;
	
	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Name cannot be longer than 100 characters")
	private String name;
	
	@NotBlank(message = "Owner name is required")
	@Size(max = 100, message = "Owner name cannot be longer than 100 characters")
	private String ownerName;
	
	@Min(value = 18, message = "Age must be at least 18")
	@Max(value = 150, message = "Age must be less than or equal to 150")
	private int age;
	
	@NotNull(message = "Gender is required")
	@Enumerated(EnumType.STRING)
	private Gender gender; 
	
	private String urlString;
	
	@Min(value = 1, message = "Number of vehicles must be at least 1")
	private int numberOf_vehicle;
	
	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
	private String moNumber;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;
	
	@NotBlank(message = "Password is required")
	private String password;
	
	@OneToMany(mappedBy = "transporter_User", cascade = CascadeType.ALL)
	private List<Vehicle> vehiclesList;
}
