package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.models.Phone;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.repository.PhoneRepo;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private PhoneRepo phoneRepo;

    public Employee createEmployee(Employee employee) throws Exception{

        Long departmentId = employee.getDepartment().getId();

        if (departmentId != null) {
            Optional<Department> departmentFromDB = departmentRepo.findById(departmentId);

            if (departmentFromDB.isPresent()) {
                employee.setDepartment(departmentFromDB.get());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
            }
        }

        Employee savedEmployee = employeeRepo.save(employee);

        // Save each phone in the list
        for (Phone phone : employee.getPhones()) {
            phone.setEmployee(employee); // Set the relationship
            phoneRepo.save(phone); // Save the phone
        }

        return savedEmployee;
    }
    
    public Iterable<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }
    
//    public void throwException() throws Exception {
//    	Exception excep = new Exception("this is an exception");
//        throw excep;
//    }
}
