package com.challenge.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "order_meal")
public class OrderMeal {

  @EmbeddedId
  private OrderMealId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("orderId")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("mealId")
  private Meal meal;

  public OrderMeal() {
  }
  
  public OrderMeal(Order order, Meal meal) {
    this.order = order;
    this.meal = meal;
    this.id = new OrderMealId(order.getId(), meal.getId());
  }

  public OrderMealId getId() {
    return id;
  }

  public Order getOrder() {
    return order;
  }

  public Meal getMeal() {
    return meal;
  }
}
