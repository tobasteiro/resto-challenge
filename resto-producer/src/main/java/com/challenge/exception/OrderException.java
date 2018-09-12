package com.challenge.exception;

import com.challenge.enumeration.RestaurantError;

public class OrderException extends RuntimeException {

	private static final long serialVersionUID = 5533386036647805963L;

	/**
	 * Default constructor.
	 */
	public OrderException(String message) {
		super("OrderException Exception: " + message);
	}

	public OrderException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderException(RestaurantError error) {
		this(error.getHttpCode() + " - " + error.getErrorDescription());
	}

}