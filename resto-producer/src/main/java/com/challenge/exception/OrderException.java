package com.challenge.exception;

import com.challenge.enumeration.RestaurantError;

public class OrderException extends RuntimeException {

  private static final long serialVersionUID = 5533386036647805963L;

  private final RestaurantError error;

  public OrderException(RestaurantError error, Throwable cause) {
    super(error.getErrorDescription(), cause);
    this.error = error;
  }

  public OrderException(RestaurantError error) {
    super(error.getHttpCode() + " - " + error.getErrorDescription());
    this.error = error;
  }

  public RestaurantError getError() {
    return error;
  }

}