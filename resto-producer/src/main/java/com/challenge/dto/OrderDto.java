package com.challenge.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

public class OrderDto {

  @NotNull
  private List<Long> meals;

  @NotNull
  private BigDecimal totalCost;

  @NotNull
  private String address;

  @NotNull
  private LocationDto location;

  
  public List<Long> getMeals() {
    return meals;
  }

  public BigDecimal getTotalCost() {
    return totalCost;
  }

  public String getAddress() {
    return address;
  }

  public LocationDto getLocation() {
    return location;
  }

}
