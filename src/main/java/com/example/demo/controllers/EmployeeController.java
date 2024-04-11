package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.models.Phone;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.repository.PhoneRepo;
import com.example.demo.services.EmployeeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/employees")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Employee", description = "The Employee API. Contains all the operations that can be performed on a user.")
public class EmployeeController {
//	
//	@Autowired
//	private EmployeeRepo employeeRepo;
//	
//	@Autowired
//	private DepartmentRepo departmentRepo;
//	
//	@Autowired
//	private PhoneRepo phoneRepo;

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/add")
//	@RequestMapping(value = "/add", method = RequestMethod.POST)  
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee savedEmployee;
		try {
			savedEmployee = employeeService.createEmployee(employee);
			return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/list")
	public @ResponseBody Iterable<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
//	@PostMapping("/add")
//	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
//
////    	Department departmentObj = new Department();
////    	
////    	if (departmentObj.getId() > 0) {
////    		Optional<Department> departmentFromDB = departmentRepo.findById(departmentObj.getId());
////    		if (departmentFromDB.isPresent()) {
////    			employee.setDepartment(departmentFromDB.get());
////    		}
////    	}
//		Long departmentId = employee.getDepartment().getId();
//
//		if (departmentId != null) {
//			Optional<Department> departmentFromDB = departmentRepo.findById(departmentId);
//
//			if (departmentFromDB.isPresent()) {
//				employee.setDepartment(departmentFromDB.get());
//			} else {
//				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
//			}
//		}
//		
//		Employee savedEmployee = employeeRepo.save(employee);
//		
//		// Save each phone in the list
//	    for (Phone phone : employee.getPhones()) {
//	        phone.setEmployee(employee); // Set the relationship
//	        phoneRepo.save(phone); // Save the phone
//	    }
//
////		Employee savedEmployee = employeeRepo.save(employee);
//		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
//
//	}

//	@GetMapping("/list")
//	public @ResponseBody Iterable<Employee> getAllStudents() {
//		return employeeRepo.findAll();
//	}

//    @GetMapping("/{id}")
//    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
//        Employee employee = employeeRepo.getEmployeeById(id);
//        return ResponseEntity.ok(employee);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
//        employee.setId(id);
//        Employee updatedEmployee = employeeRepo.updateEmployee(employee);
//        return ResponseEntity.ok(updatedEmployee);
//    }

}
