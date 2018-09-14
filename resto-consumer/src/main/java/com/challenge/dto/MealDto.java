package com.challenge.dto;

import java.math.BigDecimal;

public class MealDto {

  private Long mealId;
  private String mealName;
  private String mealDescription;
  private BigDecimal mealPrice;

  public MealDto() {
    super();
  }
  
  public MealDto(Long mealId, String mealName, String mealDescription, BigDecimal mealPrice) {
    super();
    this.mealId = mealId;
    this.mealName = mealName;
    this.mealDescription = mealDescription;
    this.mealPrice = mealPrice;
  }

  public Long getMealId() {
    return mealId;
  }

  public String getMealName() {
    return mealName;
  }

  public String getMealDescription() {
    return mealDescription;
  }

  public BigDecimal getMealPrice() {
    return mealPrice;
  }

}
