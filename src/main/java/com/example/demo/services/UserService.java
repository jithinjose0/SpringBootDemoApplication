package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.authservice.JwtGeneratorInterface;
import com.example.demo.models.Users;
import com.example.demo.repository.UserRepo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

	@Autowired
	private JwtGeneratorInterface jwtGenerator;

	@Autowired
	private UserRepo userRepository;

	public Map<String, String> signup(Users user) throws Exception {
		boolean usernameExists = userRepository.existsByUsername(user.getUsername());
		boolean emailExists = userRepository.existsByEmail(user.getEmail());

		;
		if (usernameExists || emailExists) {
			throw new Exception("Username or email already exists");
		}

//		System.out.println("user details=========================="+user.getUsername()+user.getEmail()+user.getName());
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(user.getPassword().getBytes());
		byte[] byteData = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		user.setPassword(sb.toString());
		
		userRepository.save(user);  
		String token = jwtGenerator.generateToken(user);
		System.out.println(token);
		// Create a Map to hold message and token
		Map<String, String> response = new HashMap<>();
		response.put("message", "Login successful");
		response.put("token", token);
		System.out.println("response============="+response);
		// Return the Map as JSON
		return response;
	}

	public Map<String, String> login(String username, String password) throws Exception {
		if (username.isEmpty() || password.isEmpty()) {
			throw new Exception("username or password is empty");
		}
		// Check if the user exists
		Users user = userRepository.findByUsername(username);
		if (user == null) {
			throw new Exception("Invalid username");
		}

		// Decrypt the stored password
		String decryptedPassword;
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		byte[] byteData = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		decryptedPassword = sb.toString();
		

		// Check if the password is correct
		if (!user.getPassword().equals(decryptedPassword)) { // replace with your password validation logic
			throw new Exception("Invalid password");
		}

		String token = jwtGenerator.generateToken(user);

		// Create a Map to hold message and token
		Map<String, String> response = new HashMap<>();
		response.put("message", "Login successful");
		response.put("token", token);

		// Return the Map as JSON
		return response;
	}

//	
}
