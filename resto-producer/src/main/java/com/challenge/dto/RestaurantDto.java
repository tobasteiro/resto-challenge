package com.challenge.dto;

import com.challenge.dto.builder.RestaurantDtoBuilder;

import java.math.BigDecimal;
import java.util.List;

public class RestaurantDto extends RestaurantBasicInformationDto {

  private List<MealDto> meals;
  private List<ReviewDto> reviews;

  public RestaurantDto(String logoUrl, String commercialName, String legalName, BigDecimal rating,
      String commercialEmail, String adminNumber, String address, LocationDto location,
      List<MealDto> meals, List<ReviewDto> reviews) {
    super(logoUrl, commercialName, legalName, rating, commercialEmail, adminNumber, address,
        location);
    this.meals = meals;
    this.reviews = reviews;
  }

  public List<MealDto> getMeals() {
    return meals;
  }

  public List<ReviewDto> getReviews() {
    return reviews;
  }

  public static RestaurantDtoBuilder builder() {
    return new RestaurantDtoBuilder();
  }

}
