package com.challenge.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestaurantErrorHandlerAdvice {

  private static final Logger LOG = LoggerFactory.getLogger(RestaurantErrorHandlerAdvice.class);

  /**
   * Handler exception for RestaurantException.
   * 
   * @param exception {@link RestaurantException}
   * @return {@link ResponseEntity}
   */
  @ExceptionHandler(RestaurantException.class)
  public ResponseEntity<ErrorMessage> restaurantExceptionHandling(RestaurantException exception) {
    HttpStatus httpStatus = HttpStatus.valueOf(exception.getError().getHttpCode());
    ErrorMessage error = new ErrorMessage();
    error.setError("RestaurantException");
    error.setErrorDescription(exception.getMessage());
    LOG.error("Error : " + exception.getMessage(), exception);
    return new ResponseEntity<>(error, httpStatus);
  }

  /**
   * Handler exception for OrderException.
   * 
   * @param exception {@link OrderException}
   * @return {@link ResponseEntity}
   */
  @ExceptionHandler(OrderException.class)
  public ResponseEntity<ErrorMessage> orderExceptionHandling(OrderException exception) {
    HttpStatus httpStatus = HttpStatus.valueOf(exception.getError().getHttpCode());
    ErrorMessage error = new ErrorMessage();
    error.setError("OrderException");
    error.setErrorDescription(exception.getMessage());
    LOG.error("Error : " + exception.getMessage(), exception);
    return new ResponseEntity<>(error, httpStatus);
  }


}
