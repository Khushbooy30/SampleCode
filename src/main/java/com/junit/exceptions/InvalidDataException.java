package com.junit.exceptions;

public class InvalidDataException extends RuntimeException{
	
	private String message;

	public InvalidDataException(String message) {
		super(message);
		
	}

	
	

}
