package com.example.demo;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

//import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.models.Phone;
import com.example.demo.models.Users;
//import com.example.demo.models.Department;
//import com.example.demo.models.Employee;
//import com.example.demo.models.Phone;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.repository.PhoneRepo;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

//import io.swagger.v3.oas.models.media.MediaType;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartmentRepo departmentRepo;

	@Test
	public void loginAndGetEmployees() throws Exception {
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

	    // Perform get employees with the obtained token
	    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/list")
	        .header("Authorization", "Bearer " + json.get("token"))
	    	.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.status().isOk());
	}

	
	

	@Test
	public void shouldCreateEmployeeWithExistingDepartment() throws Exception {
		
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
	    
	    
	    
	    JSONObject employee = new JSONObject();
	    employee.put("name", "helhhhloworrrrrr990");
	    employee.put("age", 90);

	    JSONObject existingDepartment = new JSONObject();
	    existingDepartment.put("id", 52);
	    employee.put("department", existingDepartment);

	    JSONArray contactNumbers = new JSONArray();

	    JSONObject firstPhone = new JSONObject();
	    firstPhone.put("number", 8847593485L);
	    contactNumbers.put(firstPhone);

	    JSONObject secondPhone = new JSONObject();
	    secondPhone.put("number", 8847593488L);
	    contactNumbers.put(secondPhone);

	    employee.put("phones", contactNumbers);

	    String employeeJson = employee.toString();
	    
	    this.mockMvc.perform(MockMvcRequestBuilders.post("/api/employees/add")
	    		.header("Authorization", "Bearer " + jsonloginRespnse.get("token"))
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(employeeJson))
	            .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void shouldCreateEmployeeWithoutLogin() throws Exception {
	    
	    
	    JSONObject employee = new JSONObject();
	    employee.put("name", "helhhhloworrrrrr990");
	    employee.put("age", 90);

	    JSONObject existingDepartment = new JSONObject();
	    existingDepartment.put("id", 52);
	    employee.put("department", existingDepartment);

	    JSONArray contactNumbers = new JSONArray();

	    JSONObject firstPhone = new JSONObject();
	    firstPhone.put("number", 8847593485L);
	    contactNumbers.put(firstPhone);

	    JSONObject secondPhone = new JSONObject();
	    secondPhone.put("number", 8847593488L);
	    contactNumbers.put(secondPhone);

	    employee.put("phones", contactNumbers);

	    String employeeJson = employee.toString();
	    
	    this.mockMvc.perform(MockMvcRequestBuilders.post("/api/employees/add")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(employeeJson))
	            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	public void shouldCreateEmployeeWithoutDepartment() throws Exception {
	    
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
	    
	    JSONObject employee = new JSONObject();
	    employee.put("name", "helhhhloworrrrrr990");
	    employee.put("age", 90);


	    JSONArray contactNumbers = new JSONArray();

	    JSONObject firstPhone = new JSONObject();
	    firstPhone.put("number", 8847593485L);
	    contactNumbers.put(firstPhone);

	    JSONObject secondPhone = new JSONObject();
	    secondPhone.put("number", 8847593488L);
	    contactNumbers.put(secondPhone);

	    employee.put("phones", contactNumbers);

	    String employeeJson = employee.toString();
	    
	    this.mockMvc.perform(MockMvcRequestBuilders.post("/api/employees/add")
	    		.header("Authorization", "Bearer " + jsonloginRespnse.get("token"))
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(employeeJson))
	            .andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
	
	@Test
	public void shouldCreateEmployeeWithNonExistDepartment () throws Exception {
		
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
	    
	    
	    
	    JSONObject employee = new JSONObject();
	    employee.put("name", "helhhhloworrrrrr990");
	    employee.put("age", 90);

	    JSONObject existingDepartment = new JSONObject();
	    existingDepartment.put("id", 103);
	    employee.put("department", existingDepartment);

	    JSONArray contactNumbers = new JSONArray();

	    JSONObject firstPhone = new JSONObject();
	    firstPhone.put("number", 8847593485L);
	    contactNumbers.put(firstPhone);

	    JSONObject secondPhone = new JSONObject();
	    secondPhone.put("number", 8847593488L);
	    contactNumbers.put(secondPhone);

	    employee.put("phones", contactNumbers);

	    String employeeJson = employee.toString();
	    
	    this.mockMvc.perform(MockMvcRequestBuilders.post("/api/employees/add")
	    		.header("Authorization", "Bearer " + jsonloginRespnse.get("token"))
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(employeeJson))
	            .andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}


        // Create a mock department for ID 52
//        Department existingDepartment = new Department();
//        existingDepartment.setId(52L);
//        existingDepartment.setName("Test Department");
//        Optional<Department> departmentOptional = Optional.of(existingDepartment);
//
//        // Mock departmentRepo behavior to return the department for ID 52
//        when(departmentRepo.findById(52L)).thenReturn(departmentOptional);
//
//        
//        Phone phones = new Phone();
//        phones.setNumber("8847593485");
//        phones.setNumber("884758893485");
//        // Create employee data with department ID 52 and two phone numbers
//        Employee employee = new Employee();
//        employee.setName("haiiiii");
//        employee.setAge(80);
//        employee.setDepartment(existingDepartment);
//        employee.setPhones(phones);
//        
//
//        // Call the service method
//        Employee savedEmployee = employeeService.createEmployee(employee);
//
//        // Assertions
//        assertNotNull(savedEmployee); // Ensure the employee is saved
//        assertEquals(employee.getName(), savedEmployee.getName()); // Verify name is set correctly
//        assertEquals(employee.getAge(), savedEmployee.getAge());  // Verify age is set correctly
//        assertEquals(employee.getDepartment(), savedEmployee.getDepartment()); // Verify department is set
//        assertEquals(2, savedEmployee.getPhones().size());  // Verify both phones are saved
//
//        // Verify departmentRepo and phoneRepo interactions (optional)
//        verify(departmentRepo, times(1)).findById(52L); // Verify department lookup by ID
//        for (Phone phone : employee.getPhones()) {
//            verify(phoneRepo, times(1)).save(phone); // Verify each phone is saved
//        }
//    }
//
//    @Test
//    public void testCreateEmployeeWithNonexistentDepartment() throws Exception {
//        // Mock departmentRepo to return empty Optional for ID 52
////        when(departmentRepo.findById(52L)).thenReturn(Optional.empty());
////        
////        Department existingDepartment = new Department();
////        existingDepartment.setId(52L);
////        existingDepartment.setName("Nonexistent Department");
////        
////        // Create employee data with department ID 52
////        Employee employee = new Employee();
////        employee.setName("nonexistentDepartmentEmployee");
////        employee.setAge(30);
////        employee.setDepartment(existingDepartment);
////
////        // Call the service method (expect exception)
////        assertThrows(Exception.class, () -> employeeService.createEmployee(employee));
//    }
}
