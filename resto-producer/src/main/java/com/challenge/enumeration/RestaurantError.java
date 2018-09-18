package com.challenge.enumeration;

public enum RestaurantError {

  RESTAURANT_NOT_FOUND(404, "Unable to find restaurant"), 
  INVALID_TOTAL_AMOUNT(400, "The total amount is lower than meal prices"),
  GEOLOCATION_API_ERROR(500, "Error calling API for geolocation"), 
  ERROR_SENDING_MESSAGE(500, "Error sending message to topic"), 
  ORDER_CREATION_ERROR(500, "Error creating order"), 
  DATABASE_ERROR(500, "Error persisting database");

  private int httpCode;
  private String errorDescription;

  private RestaurantError(int httpCode, String errorDescription) {
    this.httpCode = httpCode;
    this.errorDescription = errorDescription;
  }

  public int getHttpCode() {
    return httpCode;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

}
