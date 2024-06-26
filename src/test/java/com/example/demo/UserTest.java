package com.example.demo;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controllers.UserController;
import com.example.demo.models.Users;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.UserService;

import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;


@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

//	@MockBean
//	private UserService userService;

	
	@Test
	public void signupusernameAlreadyExsist() throws Exception {
		Users user = new Users();
	
		user.setUsername("bharathi");
		user.setName("Test Name");
		user.setPassword("testPassword");
		user.setEmail("test@example.com");
		user.setId(1L);
	    
//		String token = userService.signup(user);
//		assertNotNull(token, "Signup success");
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/users/signup")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

//	@Test
//	public void shouldReturnTokenWhenLoginIsSuccessful() throws Exception {
//	    // Arrange
//	    String expectedUsername = "bharathi";
//	    String expectedPassword = "helloworld";
//	    
//	    // Act
//	    String actualToken = userService.login(expectedUsername, expectedPassword);
//	    assertNotNull(actualToken, "Login success");
//
//	}


	 	@Test
	    public void testLoginInvalidUsername() throws Exception {
	 		
	        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"username\":\"invaliduser\", \"password\":\"password\"}"))
			        .andExpect(MockMvcResultMatchers.status().isBadRequest());
	    }

	    @Test
	    public void testLoginNullUsername() throws Exception {
	    	JSONObject user = new JSONObject();
		    user.put("username", "");
		    user.put("password", "password");
		    
		    String userJson = user.toString();

	        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(userJson))
	                .andExpect(MockMvcResultMatchers.status().isBadRequest());
	    }

	    @Test
	    public void testLoginErrorInvalidPassword() throws Exception {

	        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"username\":\"bharathi\", \"password\":\"password\"}"))
	                .andExpect(MockMvcResultMatchers.status().isBadRequest());
	    }
	    
	    

	
//	@Test
//	public void testLoginInvalidPassword() {
//		assertThrows(Exception.class, () -> userService.login("bharathi", null));
//	}

}
