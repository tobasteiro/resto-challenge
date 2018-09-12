package com.challenge.exception;

public class KafkaException extends Exception {

  private static final long serialVersionUID = -3764484272338213934L;

  /**
	 * Default constructor.
	 */
	public KafkaException(String message) {
		super("KafkaException Exception: " + message);
	}

	public KafkaException(String message, Throwable cause) {
		super(message, cause);
	}

}