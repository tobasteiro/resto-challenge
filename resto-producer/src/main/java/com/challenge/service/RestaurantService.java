package com.challenge.service;

import com.challenge.dto.MealDto;
import com.challenge.dto.RestaurantBasicInformationDto;
import com.challenge.dto.RestaurantDto;
import com.challenge.dto.RestaurantFilterDto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

public interface RestaurantService {

  /**
   * Creates a restaurant.
   * 
   * @param restaurantDto information.
   * @return restaurant generated id.
   */
  Long createRestaurant(RestaurantDto restaurantDto);

  /**
   * List restaurants by filter.
   * 
   * @param restaurantFilterDto.
   * @return a list of restaurants.
   */
  List<RestaurantDto> listRestaurants(RestaurantFilterDto restaurantFilterDto);

  /**
   * Deletes a restaurant by id.
   * 
   * @param restaurant id;
   */
  void deleteRestaurant(Long id);

  /**
   * @param restaurant id to update.
   * @param restaurantDto information.
   * @return updated Restaurant info.
   */
  RestaurantDto updateRestaurant(Long id, RestaurantBasicInformationDto restaurantDto);

  /**
   * Rates a restaurant from its reviews.
   * 
   * @param restaurant id.
   * @return calculated rating.
   */
  BigDecimal rateRestaurant(Long id);

  /**
   * Creates a new meal for a restaurant.
   * 
   * @param restaurantId.
   * @param mealDto information.
   * @return new meal information.
   */
  MealDto createMeal(Long restaurantId, @Valid MealDto mealDto);

  /**
   * Updates meal information.
   * 
   * @param restaurantId.
   * @param mealId.
   * @param mealDto.
   * @return Updated meal information.
   */
  MealDto updateMeal(Long restaurantId, Long mealId, MealDto mealDto);

  /**
   * Deletes a meal by id.
   * 
   * @param restaurant id.
   * @param mealId.
   */
  void deleteMeal(Long restaurantTd, Long mealId);

}
