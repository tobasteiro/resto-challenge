package com.challenge.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

public class OrderRequestDto {

  @NotNull
  private List<Long> meals;

  @NotNull
  private BigDecimal totalCost;

  @NotNull
  private String address;

  @NotNull
  private LocationDto location;

  @NotNull
  private String restaurantMail;

  @NotNull
  private String phoneNumber;

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

  public String getRestaurantMail() {
    return restaurantMail;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

}
