package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Students;
import com.example.demo.repository.StudentRepo;

import jakarta.validation.Valid;

//import jakarta.validation.Valid;
import java.util.List;



@RequestMapping(path = "/students")
@RestController
public class StudentController {

	@Autowired
	private StudentRepo studentsRepository;

//    public StudentController(StudentRepo studentsRepository) {
//        this.studentsRepository = studentsRepository;
//    }

	@GetMapping("/list")
	public @ResponseBody Iterable<Students> getAllStudents() {
		return studentsRepository.findAll();
	}
	
	@PostMapping("/add")
	public ResponseEntity<Students> addNewStudent(@Valid @RequestBody Students newStudent) {
		System.out.println(newStudent.toString());
		Students studobj = studentsRepository.save(newStudent);
	    return ResponseEntity.status(HttpStatus.CREATED).body(studobj);
//	    if (bindingResult.hasErrors()) {
//            // Handle validation errors
//            return new ResponseEntity<>(
//                    "validation error", 
//                    HttpStatus.BAD_REQUEST);
//        } else {
//            // Process the form data
//        	return ResponseEntity.ok("User is valid");
//        }
//	    return ResponseEntity.ok("User is valid");
	    
	}



}
