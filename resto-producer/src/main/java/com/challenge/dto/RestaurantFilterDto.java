package com.challenge.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

public class RestaurantFilterDto {

  @DecimalMax("5.0")
  @DecimalMin("0.0")
  private BigDecimal ratingFrom;
  @DecimalMax("5.0")
  @DecimalMin("0.0")
  private BigDecimal ratingTo;

  public BigDecimal getRatingFrom() {
    return ratingFrom;
  }

  public void setRatingFrom(BigDecimal ratingFrom) {
    this.ratingFrom = ratingFrom;
  }

  public BigDecimal getRatingTo() {
    return ratingTo;
  }

  public void setRatingTo(BigDecimal ratingTo) {
    this.ratingTo = ratingTo;
  }

}
