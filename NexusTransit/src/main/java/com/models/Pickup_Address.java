package com.models;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
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
@Embeddable
public class Pickup_Address {
	
	@NotBlank(message = "Street address is required")
    @Size(max = 255, message = "Street address cannot be longer than 255 characters")
    private String street_addressP;

    @Size(max = 255, message = "Landmark cannot be longer than 255 characters")
    private String landmarkP;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City cannot be longer than 100 characters")
    private String cityP;

    @NotBlank(message = "State is required")
    @Size(max = 100, message = "State cannot be longer than 100 characters")
    private String stateP;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "\\d{5,6}", message = "Pincode must be 5 or 6 digits")
    private String pincodeP;

    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country cannot be longer than 100 characters")
    private String countryP;

    @NotBlank(message = "Address type is required")
    @Size(max = 50, message = "Address type cannot be longer than 50 characters")
    private String address_typeP;

    @NotNull(message = "Created at timestamp is required")
    private LocalDateTime created_atP;
	
}
