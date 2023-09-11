package com.cwc.exception;

public class DuplicateCategoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateCategoryException() {
		
	}

	public DuplicateCategoryException(String msg) {
		super(msg);
	}
}
