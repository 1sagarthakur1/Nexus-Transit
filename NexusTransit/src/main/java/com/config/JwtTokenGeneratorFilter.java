package com.config;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.models.Transporter_User;
import com.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenGeneratorFilter {

	public String tokenGerneratorForUser(User user) {

		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + 3600 * 1000); // 1 hour

		String token = Jwts.builder().setSubject(user.getFullName())
				.claim("userId", user.getUserID()).claim("mobileNumber", user.getMobileNumber())
				.claim("userEmail", user.getEmail()).setIssuedAt(now).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_KEY_RESTAURANT).compact();

		// Print the generated token
		System.out.println(token);
		return token;
	}

	public String tokenGerneratorForTransporter_User(Transporter_User transporter_User) {

		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + 3600 * 1000); // 1 hour

		// Generate the token
		String token = Jwts.builder().setSubject("transporter_User")
				.claim("transporter_UserID", transporter_User.getId()).claim("name", transporter_User.getName())
				.claim("mobileNumber", transporter_User.getMoNumber()).setIssuedAt(now).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_KEY_CUSTOMER).compact();

		// Print the generated token
		System.out.println(token);
		return token;
	}

}
