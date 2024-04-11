package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Users;
import com.example.demo.services.UserService;


@RestController
@RequestMapping("/api/users") // Base URL for user endpoints
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<Map<String, String>> signup(@RequestBody Users user) {
		try {
			Map<String, String> response = userService.signup(user);
			return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        // Handle specific exceptions if needed (e.g., InvalidUsernameException)
	        return ResponseEntity.badRequest().body(Map.of("message", "Login failed"));
	    }
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
		try {
			String username = credentials.get("username");
			String password = credentials.get("password");
	
			Map<String, String> response = userService.login(username, password);
	
			// Return successful response with token
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        // Handle specific exceptions if needed (e.g., InvalidUsernameException)
	        return ResponseEntity.badRequest().body(Map.of("message", "Login failed"));
	    }

	}




}
