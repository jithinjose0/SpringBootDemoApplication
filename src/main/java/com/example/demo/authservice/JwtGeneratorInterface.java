package com.example.demo.authservice;


import com.example.demo.models.Users;

public interface JwtGeneratorInterface {
	String generateToken(Users user);
}