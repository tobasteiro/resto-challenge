package com.challenge.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderMealId implements Serializable {

  private static final long serialVersionUID = -6371705373684525670L;

  @Column(name = "order_id")
  private Long orderId;

  @Column(name = "meal_id")
  private Long mealId;

  public OrderMealId() {
  }

  public OrderMealId(Long orderId, Long mealId) {
    super();
    this.orderId = orderId;
    this.mealId = mealId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public Long getMealId() {
    return mealId;
  }

}
