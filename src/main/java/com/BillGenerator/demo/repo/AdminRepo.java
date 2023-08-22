package com.BillGenerator.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillGenerator.demo.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

	boolean existsByEmail(String email);
	Optional<Admin> findByEmailAndPassword(String email, String password);

}
