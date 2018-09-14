package com.challenge.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class ReviewDto {

  private Long reviewId;
  @NotNull
  private String reviewName;
  @NotNull
  private String reviewDescription;
  @NotNull
  @DecimalMax("5.0")
  @DecimalMin("0.0")
  private Integer rating;

  public ReviewDto() {
    super();
  }

  public ReviewDto(String reviewName, String reviewDescription, Integer rating) {
    super();
    this.reviewName = reviewName;
    this.reviewDescription = reviewDescription;
    this.rating = rating;
  }

  public Long getReviewId() {
    return reviewId;
  }
  
  public void setReviewId(Long reviewId) {
    this.reviewId = reviewId;
  }

  public String getReviewName() {
    return reviewName;
  }

  public String getReviewDescription() {
    return reviewDescription;
  }

  public Integer getRating() {
    return rating;
  }

}
