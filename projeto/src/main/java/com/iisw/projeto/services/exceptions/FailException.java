package com.iisw.projeto.services.exceptions;

public class FailException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FailException(String e) {
		super (e);
	}

}
