package com.BillGenerator.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillGenerator.demo.customException.BillNotExist;
import com.BillGenerator.demo.customException.ConsumerNotExist;
import com.BillGenerator.demo.customException.InvalidUsername;
import com.BillGenerator.demo.customException.WrongCredentials;
import com.BillGenerator.demo.dto.Request;
import com.BillGenerator.demo.dto.BillTransfer;
import com.BillGenerator.demo.model.Admin;
import com.BillGenerator.demo.model.Bill;
import com.BillGenerator.demo.model.Consumer;
import com.BillGenerator.demo.repo.AdminRepo;
import com.BillGenerator.demo.repo.BillRepo;
import com.BillGenerator.demo.repo.ConsumerRepo;


@Service
public class AdminServiceImp implements AdminService {
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	ConsumerRepo consumerRepo;
	
	@Autowired
	BillRepo billRepo;

	@Override
	public Bill addBill(int consumerId,int year,int month,Long unitsConsumed) {
		if(!consumerRepo.existsByConsumerId(consumerId))throw new ConsumerNotExist("Consumer with id: "+consumerId+" not exist");
		Consumer consumer = consumerRepo.findById(consumerId).get();
		Bill newBill = new Bill();
		newBill.setConsumerId(consumer);
		newBill.setYear(year);
		newBill.setMonth(month);
		newBill.setUnitsConsumed(unitsConsumed);
		if(consumer.getConnectionType().equalsIgnoreCase("domestic")) {
			newBill.setBillAmount(unitsConsumed*2);
		}else {
			newBill.setBillAmount(unitsConsumed*4);
		}
		billRepo.save(newBill);
		return newBill;
	}

	@Override
	public boolean validateAdmin(Request adminRequest) {
		if(adminRepo.existsByEmail(adminRequest.getUsername())) {
			Optional<Admin> admin =  adminRepo.findByEmailAndPassword(adminRequest.getUsername(), adminRequest.getPassword());
			if(admin.isPresent()) {
				return true;
			}else throw new WrongCredentials("Wrong Credentials. Please check your password");
		}else throw new InvalidUsername("Username doesn't exist");
	}

	@Override
	public String deleteConsumer(int consumerId) {
		boolean isConsumerExist = consumerRepo.existsById(consumerId);
		if(!isConsumerExist) {
			throw new ConsumerNotExist("Consumer with id "+consumerId +" not exist");
		}
		consumerRepo.deleteById(consumerId);
		return "Consumer with id "+consumerId+" is deleted";
	}

	@Override
	public Bill updateUnitsConsumed(int consumerId, int year, int month, Long newUnits) {
		boolean isConsumerExist = consumerRepo.existsById(consumerId);
		if(!isConsumerExist) {
			throw new ConsumerNotExist("Consumer with id "+consumerId +" not exist");
		}
		Consumer consumer = consumerRepo.findById(consumerId).get();
		Bill bill = billRepo.findByConsumerIdAndYearAndMonth(consumer, year, month);
		if(bill==null)
			throw new BillNotExist("Bill for year: "+year+" and month: "+month+" not exist");
		bill.setUnitsConsumed(newUnits);
		if(consumer.getConnectionType().equalsIgnoreCase("domestic")) {
			bill.setBillAmount(newUnits*2);
		}else {
			bill.setBillAmount(newUnits*4);
		}
		billRepo.save(bill);
		return bill;
		
	}

}
