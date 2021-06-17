package br.com.carwash.exception;

import javax.ws.rs.core.Response.Status;

public class SenhaIvalidaException extends Exception {
	
	private static final long serialVersionUID = 1L;
	Status statusCode = Status.FORBIDDEN;

	public SenhaIvalidaException() {
		super();
	}
	public SenhaIvalidaException(String string) {
		super(string);
	}
	public Status getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Status statusCode) {
		this.statusCode = statusCode;
	}
}
