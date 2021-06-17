package br.com.carwash.exception;

import javax.ws.rs.core.Response.Status;

public class NotValidDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Status statusCode = Status.INTERNAL_SERVER_ERROR;
	
	public NotValidDataException() {
		super();
	}
	public NotValidDataException(Status statusCode, String string) {
		super(string);
		this.statusCode = statusCode;
	}

	public Status getStatusCode() {
		return statusCode;
	}
}
