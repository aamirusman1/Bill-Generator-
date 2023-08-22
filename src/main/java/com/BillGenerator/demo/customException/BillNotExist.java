package com.BillGenerator.demo.customException;

public class BillNotExist extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BillNotExist(String message) {
		super(message);
	}

}
