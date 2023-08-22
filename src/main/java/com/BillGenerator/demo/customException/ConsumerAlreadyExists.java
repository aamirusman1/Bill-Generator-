package com.BillGenerator.demo.customException;


public class ConsumerAlreadyExists extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ConsumerAlreadyExists(String message){
		super(message);
	}

}
