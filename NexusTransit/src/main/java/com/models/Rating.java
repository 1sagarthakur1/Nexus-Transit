package com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
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
public class Rating {
	

    @Id
    @JsonProperty(access = Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
    @SequenceGenerator(name = "userGenerator", sequenceName = "userGen", allocationSize = 1, initialValue = 1)
    private int id;
    
    @Min(value = 1, message = "Rating should not be less than 1")
    @Max(value = 5, message = "Rating should not be greater than 5")
    private int rating;
    
    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description should not exceed 255 characters")
    private String description;
    
    @NotNull(message = "User is mandatory")
    @ManyToOne // assuming a many-to-one relationship
    @JsonIgnore
    private User users;
    
    @NotNull(message = "Vehicle is mandatory")
    @ManyToOne // assuming a many-to-one relationship
    @JsonIgnore
    private Vehicle vehicle;
}
