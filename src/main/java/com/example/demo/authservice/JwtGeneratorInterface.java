package com.example.demo.authservice;


import com.example.demo.models.Users;

import io.jsonwebtoken.security.InvalidKeyException;

public interface JwtGeneratorInterface {
	String generateToken(Users user);
}