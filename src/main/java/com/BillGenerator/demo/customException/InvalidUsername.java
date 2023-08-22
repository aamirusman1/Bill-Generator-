package com.BillGenerator.demo.customException;

public class InvalidUsername extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidUsername(String message) {
		super(message);
	}

}
