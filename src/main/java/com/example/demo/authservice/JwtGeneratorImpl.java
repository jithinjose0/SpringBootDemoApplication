package com.example.demo.authservice;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.example.demo.models.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface {
	private SecretKey getSigningKey() throws Exception {
		byte[] keyBytes = Decoders.BASE64.decode("5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437");
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public String generateToken(Users user) {
		Map<String, String> claims = new HashMap<>();
		claims.put("username", user.getUsername());
		claims.put("name", user.getName());
		claims.put("email", user.getEmail());
		
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, 30);

		Date expiration = cal.getTime();
		String token = "";
		try {
			token = Jwts.builder().subject(user.getId().toString()) // Use user ID instead of password
					.issuer("UserData").signWith(getSigningKey()).claims(claims).issuedAt(new Date()).expiration(expiration)
					.compact();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return token;
	}
}