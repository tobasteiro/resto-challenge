package com.challenge.dto;

import java.math.BigDecimal;

public class RateRestaurantResponseDto {
  public Long restaurantId;
  public ReviewDto reviewDto;
  public BigDecimal totalRating;

  public RateRestaurantResponseDto() {
    super();
  }

  public RateRestaurantResponseDto(Long restaurantId, ReviewDto reviewDto, BigDecimal totalRating) {
    super();
    this.restaurantId = restaurantId;
    this.reviewDto = reviewDto;
    this.totalRating = totalRating;
  }

  public Long getRestaurantId() {
    return restaurantId;
  }

  public ReviewDto getReviewDto() {
    return reviewDto;
  }

  public BigDecimal getTotalRating() {
    return totalRating;
  }

}
