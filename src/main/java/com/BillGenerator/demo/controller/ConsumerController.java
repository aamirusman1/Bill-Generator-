package com.BillGenerator.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BillGenerator.demo.dto.Request;
import com.BillGenerator.demo.model.Bill;
import com.BillGenerator.demo.model.Consumer;
import com.BillGenerator.demo.service.ConsumerService;

@RestController
public class ConsumerController {

	@Autowired
	ConsumerService consumerService;
	
	@GetMapping("/")
	String testMethod() {
		return "Successful";
	}
	
	@PostMapping("/consumer/register")
	ResponseEntity<String> registerConsumer(@RequestBody Consumer consumer ){	
			Consumer newConsumer = consumerService.addConsumer(consumer);
			int consumerId = newConsumer.getConsumerId();
			String display = "Successfully Registered with consumer id "+consumerId+".";
			return new ResponseEntity<String>(display,HttpStatus.OK);		
	}
	
	@GetMapping("/consumer/getBill")
	ResponseEntity<Bill> getBillByConsumerIdAndYearAndMonth(@RequestBody Request request,@RequestParam("id") int consumerId, @RequestParam("year") int year, @RequestParam("month") int month){
		consumerService.validateConsumer(request);
		Bill bill = consumerService.getBill(consumerId, year, month);
		return new ResponseEntity<Bill>(bill,HttpStatus.OK);
	}
	
	@GetMapping("/consumer/getAllBill")
	ResponseEntity<List<Bill>> getBillByConsumerId(@RequestBody Request request,@RequestParam("id") int consumerId){
		consumerService.validateConsumer(request);
		List<Bill> bill = consumerService.getAllBill(consumerId);
		return new ResponseEntity<>(bill,HttpStatus.OK);
	}

	

}
