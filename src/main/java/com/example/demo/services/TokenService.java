package com.example.demo.services;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

	@Autowired
	private UserRepo userRepository;

	private SecretKey getSigningKey() throws Exception {
		byte[] keyBytes = Decoders.BASE64.decode("5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437");
		return Keys.hmacShaKeyFor(keyBytes);
	}


	public Claims validatetoken(String token) throws Exception {
		System.out.println("Inside validation : " + token);
//		String token = token;
		if (token == null) {
			throw new Exception("Token not found in request header");
		}
		Claims claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
		Date expirationDate = claims.getExpiration();
		Date currentDate = new Date();

		if (expirationDate.getTime() > currentDate.getTime()) {
			String username = (String) claims.get("username");
			System.out.println("Username: " + username);
			if (userRepository.existsByUsername(username)) {
//				Users user = userRepository.findByUsername(username);
				return claims;
			} else {
				throw new Exception("User not found");
			}
		} else {
			throw new Exception("Token expired");
		}
	}
}
