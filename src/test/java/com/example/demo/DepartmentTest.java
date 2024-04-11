package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void testCreateDepartment_Success() throws Exception {
		// Prepare login credentials
	    Map<String, String> credentials = new HashMap<>();
	    credentials.put("username", "bharathi");
	    credentials.put("password", "helloworld");

	    // Perform login
	    MvcResult loginResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login")
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(new ObjectMapper().writeValueAsString(credentials)))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andReturn();
	    
	    // Extract token from login response
	    String reponse = loginResult.getResponse().getContentAsString();
	    JSONObject jsonloginRespnse = new JSONObject(reponse);
	    
	    JSONObject Department = new JSONObject();
	    Department.put("id", 100L);
	    Department.put("name", "CS_dep");
	    
	    String DepJson = Department.toString();
	    
	    this.mockMvc.perform(MockMvcRequestBuilders.post("/api/departments/add")
	    		.header("Authorization", "Bearer " + jsonloginRespnse.get("token"))
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(DepJson))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	
	}
	
	@Test
    public void testGetAllDepartments_Success() throws Exception {
		 // Prepare login credentials
	    Map<String, String> credentials = new HashMap<>();
	    credentials.put("username", "bharathi");
	    credentials.put("password", "helloworld");


	    // Perform login
	    MvcResult signupResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login")
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(new ObjectMapper().writeValueAsString(credentials)))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andReturn();
	    
	    System.out.println("signupResult: "+signupResult);
	    // Extract token from login response
	    String reponse = signupResult.getResponse().getContentAsString();
	    JSONObject json = new JSONObject(reponse);
	    System.out.println("token: "+json.get("token"));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/departments/list")
		        .header("Authorization", "Bearer " + json.get("token"))
		    	.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk());
	
	}
	

}
