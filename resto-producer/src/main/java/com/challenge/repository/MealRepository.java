package com.challenge.repository;

import com.challenge.model.Meal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {

  /**
   * @param mealId.
   * @param restaurantId.
   * @return Meal by id and restaurant id.
   */
  Meal findOneByIdAndRestaurantId(Long mealId, Long restaurantId);

  /**
   * @param meal ids.
   * @return a list with meal information.
   */
  List<Meal> findByIdIn(List<Long> meals);

}
