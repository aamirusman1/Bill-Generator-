package com.BillGenerator.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BillGenerator.demo.customException.ConsumerNotExist;
import com.BillGenerator.demo.dto.Request;
import com.BillGenerator.demo.dto.BillTransfer;
import com.BillGenerator.demo.model.Bill;
import com.BillGenerator.demo.model.Consumer;
import com.BillGenerator.demo.repo.ConsumerRepo;
import com.BillGenerator.demo.service.AdminService;
import com.BillGenerator.demo.service.ConsumerService;

@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	ConsumerService consumerService;
	
	@Autowired
	ConsumerRepo consumerRepo;
	
	@PostMapping("/admin/generateBill")
	ResponseEntity<Bill> addBill(@RequestBody Request adminRequest, @RequestParam("id") int consumerId, @RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("units") Long unitsConsumed){
		adminService.validateAdmin(adminRequest);
		Bill newBill = adminService.addBill(consumerId,year,month,unitsConsumed);
		return new ResponseEntity<>(newBill,HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/admin/getBill")
	ResponseEntity<Bill> getBillByConsumerIdAndYearAndMonth(@RequestBody Request request,@RequestParam("id") int consumerId, @RequestParam("year") int year, @RequestParam("month") int month){
		adminService.validateAdmin(request);
		Bill bill = consumerService.getBill(consumerId, year, month);
		return new ResponseEntity<Bill>(bill,HttpStatus.OK);
	}
	
	@GetMapping("/admin/getAllBill")
	ResponseEntity<List<Bill>> getBillByConsumerId(@RequestBody Request request,@RequestParam("id") int consumerId){
		adminService.validateAdmin(request);
		List<Bill> bill = consumerService.getAllBill(consumerId);
		return new ResponseEntity<>(bill,HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/deleteConsumer")
	ResponseEntity<String> deleteConsumer(@RequestBody Request adminRequest,@RequestParam("id") int consumerId){
		adminService.validateAdmin(adminRequest);	
			String consumerDeleted = adminService.deleteConsumer(consumerId);
			return new ResponseEntity<>(consumerDeleted,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/admin/updateUnits")
	ResponseEntity<Bill> updateUnitsConsumed(@RequestBody Request request, @RequestParam("id") int consumerId, @RequestParam("year") int year, @RequestParam("month") int month,@RequestParam("units") Long unitsConsumed){
		adminService.validateAdmin(request);	
		Bill newBill = adminService.updateUnitsConsumed(consumerId, year, month, unitsConsumed);
		return new ResponseEntity<>(newBill, HttpStatus.OK);
	}
	

}
