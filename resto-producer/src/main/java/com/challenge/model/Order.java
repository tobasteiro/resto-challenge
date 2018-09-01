package com.challenge.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderMeal> orderMeals = new ArrayList<>();

  @Column(name = "total_cost")
  private BigDecimal totalCost;

  @Column(name = "address", nullable = false)
  private String address;

  @Embedded
  @AttributeOverrides({ @AttributeOverride(name = "latitude", column = @Column(name = "latitude")),
      @AttributeOverride(name = "longitude", column = @Column(name = "longitude")) })
  private Location location;

  public Order() {
    super();
  }

  public void addMeal(Meal meal) {
    OrderMeal orderMeal = new OrderMeal(this, meal);
    orderMeals.add(orderMeal);
  }

  public void removeMeal(Meal meal) {
    orderMeals = orderMeals.stream().filter(om -> om.getId().getMealId() != meal.getId())
        .collect(Collectors.toList());
  }

  public Long getId() {
    return id;
  }

  public List<OrderMeal> getOrderMeals() {
    return orderMeals;
  }

  public void setOrderMeals(List<OrderMeal> orderMeals) {
    this.orderMeals = orderMeals;
  }

  public BigDecimal getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(BigDecimal totalCost) {
    this.totalCost = totalCost;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

}
