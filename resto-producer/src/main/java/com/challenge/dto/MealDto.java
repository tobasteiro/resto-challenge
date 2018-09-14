package com.challenge.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

public class MealDto {

  private Long mealId;
  @NotNull
  private String mealName;
  @NotNull
  private String mealDescription;
  @NotNull
  @DecimalMax(value = "9999.0")
  private BigDecimal mealPrice;

  public MealDto(Long mealId, String mealName, String mealDescription, BigDecimal mealPrice) {
    super();
    this.mealId = mealId;
    this.mealName = mealName;
    this.mealDescription = mealDescription;
    this.mealPrice = mealPrice;
  }
  
  public MealDto() {
    super();
  }

  public Long getMealId() {
    return mealId;
  }

  public void setMealId(Long mealId) {
    this.mealId = mealId;
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
