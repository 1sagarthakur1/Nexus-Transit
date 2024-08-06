package com.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repository.VehicleRepo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
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
public class Vehicle {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicleGenerator")
    @SequenceGenerator(name = "vehicleGenerator", sequenceName = "vehicleGen", allocationSize = 1, initialValue = 1)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    
    @NotBlank(message = "Vehicle name is mandatory")
    @Size(max = 100, message = "Vehicle name cannot exceed 100 characters")
    private String vehicleName;
    
    @NotBlank(message = "Driver name is mandatory")
    @Size(max = 100, message = "Driver name cannot exceed 100 characters")
    private String driverName;
    
    @NotBlank(message = "Driver mobile number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Driver mobile number should be a valid 10-digit number")
    private String driver_mobileNO;
    
    @NotBlank(message = "Model number is mandatory")
    @Size(max = 50, message = "Model number cannot exceed 50 characters")
    private String modelNO;
    
    @NotBlank(message = "Vehicle type is mandatory")
    @Size(max = 50, message = "Vehicle type cannot exceed 50 characters")
    private String vehicle_type;
    
    @NotBlank(message = "Vehicle number is mandatory")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "Vehicle number should be alphanumeric and may contain hyphens")
    @Column(unique = true)
    private String vehicleNo;
    
    @Min(value = 1, message = "Number of wheels must be at least 1")
    @Max(value = 18, message = "Number of wheels cannot be more than 18")
    private int wheel_no;
    
    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    private String image; // Assuming image URL or base64 encoded string
    
    @NotBlank(message = "Owner name is mandatory")
    @Size(max = 100, message = "Owner name cannot exceed 100 characters")
    private String ownerName;
    
    @NotNull(message = "Active status is mandatory")
    @Pattern(regexp = "^(active|inactive)$", message = "Active status must be either 'active' or 'inactive'")
    private String active_ornot;
    
    @NotNull(message = "Booking status is mandatory")
    private Boolean booking_ornot;
    
    @ManyToOne
    @JoinColumn(name = "transporter_User_id", nullable = false)
    @JsonIgnore
    private Transporter_User transporter_User;
    
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<VehicleSlote> vehicleSlotes;
    
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Rating> ratings;
    
    
}
