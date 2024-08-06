package com.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

	@NotNull(message = "email is required")
	@NotBlank(message = "Enter vaild email")
	private String email;
	
	@NotNull(message = "password is required")
	@NotBlank(message = "Enter vaild pssword")
	private String password;
}
