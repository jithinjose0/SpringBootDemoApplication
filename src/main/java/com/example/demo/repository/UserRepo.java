package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.models.Users;

@Repository
public interface UserRepo extends CrudRepository<Users, Long> {

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Users findByUsername(String username);

	
}
