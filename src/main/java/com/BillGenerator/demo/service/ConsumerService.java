package com.BillGenerator.demo.service;

import java.util.List;

import com.BillGenerator.demo.dto.Request;
import com.BillGenerator.demo.model.Bill;
import com.BillGenerator.demo.model.Consumer;

public interface ConsumerService {

Consumer addConsumer(Consumer consumer);
	
	Bill getBill(int consumerId, int year, int month);
	
	List<Bill> getAllBill(int consumerId);
	
	boolean validateConsumer(Request consumerRequest);

}
