package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Department;

@Repository
public interface DepartmentRepo extends CrudRepository<Department, Integer> {
}
