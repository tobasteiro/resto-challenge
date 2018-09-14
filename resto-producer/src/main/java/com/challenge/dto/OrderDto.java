package com.challenge.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

public class OrderDto {

  @NotNull
  private List<MealDto> meals;

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

  public OrderDto(List<MealDto> meals, BigDecimal totalCost, String address, LocationDto location,
      String restaurantMail, String phoneNumber) {
    super();
    this.meals = meals;
    this.totalCost = totalCost;
    this.address = address;
    this.location = location;
    this.restaurantMail = restaurantMail;
    this.phoneNumber = phoneNumber;
  }

  public OrderDto() {
    super();
  }

  public List<MealDto> getMeals() {
    return meals;
  }

  public void setMeals(List<MealDto> meals) {
    this.meals = meals;
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
