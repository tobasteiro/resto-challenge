package com.challenge.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

  private List<MealDto> meals;
  private BigDecimal totalCost;
  private String address;
  private LocationDto location;
  private String restaurantMail;
  private String phoneNumber;

  public OrderDto() {
    super();
  }

  public List<MealDto> getMeals() {
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
