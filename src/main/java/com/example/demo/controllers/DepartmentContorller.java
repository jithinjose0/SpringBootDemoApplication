package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.repository.DepartmentRepo;



@RestController
@RequestMapping("/api/departments")
public class DepartmentContorller {
	
	@Autowired
    private DepartmentRepo departmentrep;
	
	 @PostMapping("/add")
    public ResponseEntity<Department> createDepartment(@RequestBody Department depart) {
    	Department createdDepartment = departmentrep.save(depart);
        return ResponseEntity.ok(createdDepartment);
    }
	 
	@GetMapping("/list")
	public @ResponseBody Iterable<Department> getAllStudents() {
		return departmentrep.findAll();
	}

}
