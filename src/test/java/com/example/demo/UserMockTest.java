package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.controllers.UserController;
import com.example.demo.models.Users;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserMockTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepo userRepo;
	
	@Test
	public void shouldReturnTokenWhensignup() throws Exception {
		Users user = new Users();
	
		user.setUsername("test123");
		user.setName("Test Name");
		user.setPassword("testPassword");
		user.setEmail("test123@example.com");
		user.setId(8L);
		
//		String token = userService.signup(user);
//		assertNotNull(token, "Signup success");
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/users/signup")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
