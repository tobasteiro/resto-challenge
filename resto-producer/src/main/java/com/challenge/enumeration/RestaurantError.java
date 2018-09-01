package com.challenge.enumeration;

public enum RestaurantError {

  RESTAURANT_NOT_FOUND(404, "Unable to find restaurant"), 
  INVALID_TOTAL_AMOUNT(422, "The total amount is lower than meal prices.");

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
