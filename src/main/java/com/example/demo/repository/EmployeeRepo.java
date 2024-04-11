package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;


@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

	Employee getEmployeeById(Long id);

//	Employee updateEmployee(Employee employee);

//	Employee deleteEmployee(Long id);
}
