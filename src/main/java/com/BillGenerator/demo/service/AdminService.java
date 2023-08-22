package com.BillGenerator.demo.service;

import com.BillGenerator.demo.dto.Request;
import com.BillGenerator.demo.dto.BillTransfer;
import com.BillGenerator.demo.model.Bill;
import com.BillGenerator.demo.model.Consumer;

public interface AdminService {

	Bill addBill(int consumerId,int year,int month,Long unitsConsumed);
	boolean validateAdmin(Request adminRequest);
	String deleteConsumer(int consumerId);
	Bill updateUnitsConsumed(int consumerId, int year, int month, Long newUnits);
}
