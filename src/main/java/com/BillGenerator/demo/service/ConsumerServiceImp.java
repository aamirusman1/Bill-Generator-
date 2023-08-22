package com.BillGenerator.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillGenerator.demo.customException.ConsumerAlreadyExists;
import com.BillGenerator.demo.customException.ConsumerNotExist;
import com.BillGenerator.demo.customException.InvalidUsername;
import com.BillGenerator.demo.customException.WrongCredentials;
import com.BillGenerator.demo.dto.Request;
import com.BillGenerator.demo.model.Bill;
import com.BillGenerator.demo.model.Consumer;
import com.BillGenerator.demo.repo.BillRepo;
import com.BillGenerator.demo.repo.ConsumerRepo;

@Service
public class ConsumerServiceImp implements ConsumerService {

	@Autowired
	ConsumerRepo consumerRepo;
	
	@Autowired
	BillRepo billRepo;

	@Override
	public Consumer addConsumer(Consumer consumer) {
		String email = consumer.getEmail();
		boolean isEmailPresent = consumerRepo.existsByEmail(email);
		System.out.println(isEmailPresent);
		if(isEmailPresent) {
			throw new ConsumerAlreadyExists("Consumer with this email already registered");
		}		
			Consumer savedConsumer = consumerRepo.save(consumer);
			return savedConsumer;
	}


	@Override
	public Bill getBill(int consumerId, int year, int month) {
		if(!consumerRepo.existsByConsumerId(consumerId))throw new ConsumerNotExist("Consumer with id: "+consumerId+" not exist");
		Consumer consumer = consumerRepo.findById(consumerId).get();
		Bill bill = billRepo.findByConsumerIdAndYearAndMonth(consumer, year, month);
		return bill;
	}

	@Override
	public List<Bill> getAllBill(int consumerId) {
		if(!consumerRepo.existsByConsumerId(consumerId))throw new ConsumerNotExist("Consumer with id: "+consumerId+" not exist");
		Consumer consumer = consumerRepo.findById(consumerId).get();
		List<Bill> bills = billRepo.findByConsumerId(consumer);
		return bills;
	}


	@Override
	public boolean validateConsumer(Request consumerRequest) {
		String username = consumerRequest.getUsername();
		String password = consumerRequest.getPassword();
		if(!consumerRepo.existsByEmail(username))
			throw new InvalidUsername("Consumer with this username not exist");
		Optional<Consumer> consumer = consumerRepo.findByEmailAndPassword(username, password);
		if(consumer.isPresent())
			return true;
		else throw new WrongCredentials("Wrong Credentials. Please check your password");
	}


}
