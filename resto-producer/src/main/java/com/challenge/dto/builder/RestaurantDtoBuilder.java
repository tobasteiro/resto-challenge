package com.challenge.dto.builder;

import com.challenge.dto.LocationDto;
import com.challenge.dto.MealDto;
import com.challenge.dto.RestaurantDto;
import com.challenge.dto.ReviewDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

public class RestaurantDtoBuilder {

  public String logoUrl;
  public String commercialName;
  public String legalName;
  public BigDecimal rating;
  public String commercialEmail;
  public String adminNumber;
  public String address;
  public LocationDto location;
  public List<MealDto> meals;
  public List<ReviewDto> reviews;

  public RestaurantDtoBuilder with(Consumer<RestaurantDtoBuilder> builderFunction) {
    builderFunction.accept(this);
    return this;
  }

  public RestaurantDto build() {
    return new RestaurantDto(logoUrl, commercialName, legalName, rating, commercialEmail,
        adminNumber, address, location, meals, reviews);
  }

}
