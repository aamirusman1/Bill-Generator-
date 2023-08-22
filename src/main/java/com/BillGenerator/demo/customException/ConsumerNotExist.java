package com.BillGenerator.demo.customException;

public class ConsumerNotExist extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConsumerNotExist(String message) {
		super(message);
	}

}
