package com.nazmul.trip.exception;

/**
 * Data Not Found Exception Handler
 *
 * @author Nazmul
 *
 */
public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = -8022882928186487643L;

	String message;

	public DataNotFoundException(String message) {
		super(message, null, true, false);
		this.message = message;
	}

	public DataNotFoundException(String message, Throwable t) {
		super(message, t);
		this.message = message;
	}

	@Override
	public String toString() {
		return ("Exception (DataNotFoundException): " + message);
	}

}
