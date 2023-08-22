package com.BillGenerator.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillGenerator.demo.model.Consumer;

public interface ConsumerRepo extends JpaRepository<Consumer, Integer> {

	boolean existsByEmail(String email);
	Optional<Consumer> findByEmailAndPassword(String email, String password);
	
	boolean existsByConsumerId(int consumerId);

}
