package com.BillGenerator.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillGenerator.demo.model.Bill;
import com.BillGenerator.demo.model.Consumer;

public interface BillRepo extends JpaRepository<Bill, Integer> {
	
	Bill findByConsumerIdAndYearAndMonth(Consumer consumerId, int year, int month);
	
	List<Bill> findByConsumerId(Consumer consumerId);

}
