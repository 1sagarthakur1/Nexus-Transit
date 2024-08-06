package com.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


@Entity
public class VehicleSlote {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slotGenerator")
    @SequenceGenerator(name = "slotGenerator", sequenceName = "slotGen", allocationSize = 1, initialValue = 1)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    
//    @NotBlank(message = "Slot number is mandatory")
//    @Size(max = 50, message = "Slot number cannot exceed 50 characters")
//    private String sloteNO;
    
//    @NotBlank(message = "Slot creator is mandatory")
    @Size(max = 100, message = "Slot creator cannot exceed 100 characters")
    private String sloteCreater;
    
    @NotBlank(message = "Starting point is mandatory")
    @Size(max = 100, message = "Starting point cannot exceed 100 characters")
    private String starting_point;
    
    @NotBlank(message = "Ending point is mandatory")
    @Size(max = 100, message = "Ending point cannot exceed 100 characters")
    private String ending_point;
    
    @NotNull(message = "Date and time are mandatory")
    private LocalDateTime localDateTime;
    
//    @NotNull(message = "Booking status is mandatory")
    private Boolean booking_ornot;
    
//    @NotNull(message = "Acceptance status is mandatory")
    private Boolean acepten_ornot;
    
    @Min(value = 1, message = "Distance in KM must be at least 1")
    private int distance_KM;
    
    @Min(value = 0, message = "Price per KM must be at least 0")
    private int price_perKM;
    
//    @NotNull(message = "Luggage is mandatory")
    @ManyToOne
    @JsonIgnore
    private Luggage luggage;
    
//    @NotNull(message = "Vehicle is mandatory")
    @ManyToOne
    @JsonIgnore
    private Vehicle vehicle;
    
    private boolean deliveryOrNot;
}
