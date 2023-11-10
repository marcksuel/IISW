package com.iisw.projeto.services.exceptions;

import com.iisw.projeto.models.response.ExceptionsResponse.NotFound;

public class ResourceNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String msg, Object id) {			
	super (msg + id + " ResourceNotFound");
	}

}
