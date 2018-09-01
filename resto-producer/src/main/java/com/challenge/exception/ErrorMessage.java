package com.challenge.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ErrorMessage implements Serializable {

  private static final long serialVersionUID = 5981890517203086196L;

  private String error;
  private String errorDescription;
  @JsonIgnore
  private HttpStatus httpStatus;

  public ErrorMessage() { }

  
  /**
   * @param error code.
   * @param errorDescription description 
   * @param httpStatus HTTP status
   */
  public ErrorMessage(String error, String errorDescription, HttpStatus httpStatus) {
    this.error = error;
    this.errorDescription = errorDescription;
    this.httpStatus = httpStatus;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }
  
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public boolean hasError() {
    return httpStatus != null && !HttpStatus.OK.equals(httpStatus);
  }
}
