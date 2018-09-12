package com.challenge.exception;

public class GeolocationException extends Exception {

	private static final long serialVersionUID = 5533386036647805963L;

	/**
	 * Default constructor.
	 */
	public GeolocationException(String message) {
		super("GeolocationException Exception: " + message);
	}

	public GeolocationException(String message, Throwable cause) {
		super(message, cause);
	}

}