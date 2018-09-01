package com.challenge.exception;

import com.challenge.enumeration.RestaurantError;

public class RestaurantException extends RuntimeException {

  private static final long serialVersionUID = 5533386036647805963L;
  
  /**
   * Default constructor.
   */
  public RestaurantException(String message) {
    super("Restaurant Exception: " + message);
  }
  
  
  public RestaurantException(String message, Throwable cause) {
    super(message, cause);
  }

  public RestaurantException(RestaurantError restaurantError) {
    this(restaurantError.getHttpCode() + " - " + restaurantError.getErrorDescription());
  }

}
