package com.BillGenerator.demo.customException;

public class WrongCredentials extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public WrongCredentials(String message) {
		super(message);
	}

}
