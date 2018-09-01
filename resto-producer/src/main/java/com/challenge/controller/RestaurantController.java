package com.challenge.controller;

import com.challenge.dto.MealDto;
import com.challenge.dto.RestaurantBasicInformationDto;
import com.challenge.dto.RestaurantDto;
import com.challenge.dto.RestaurantFilterDto;
import com.challenge.service.RestaurantService;
import com.challenge.util.SwaggerTags;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
  
  @Autowired
  private RestaurantService restaurantService;

  @ApiOperation(value = "Creates a restaurant.", tags = { SwaggerTags.TAG_RESTAURANT })
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public Long createRestaurant(@RequestBody @Valid RestaurantDto restaurantDto) {
    return restaurantService.createRestaurant(restaurantDto);
  }

  @ApiOperation(value = "Return the list of restaurants.", tags = { SwaggerTags.TAG_RESTAURANT })
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List<RestaurantDto> listRestaurants(
      @ModelAttribute @Valid RestaurantFilterDto restaurantFilterDto) {
    return restaurantService.listRestaurants(restaurantFilterDto);
  }
  
  @ApiOperation(value = "Deletes a restaurant.", tags = { SwaggerTags.TAG_RESTAURANT })
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteRestaurant(@PathVariable Long id) {
    restaurantService.deleteRestaurant(id);
  }

  @ApiOperation(value = "Update a restaurant.", tags = { SwaggerTags.TAG_RESTAURANT })
  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  public RestaurantDto updateRestaurant(@PathVariable Long id,
      @RequestBody RestaurantBasicInformationDto restaurantDto) {
    return restaurantService.updateRestaurant(id, restaurantDto);
  }
  
  @ApiOperation(value = "Rate a restaurant.", tags = { SwaggerTags.TAG_RESTAURANT })
  @RequestMapping(value = "/{id}/rate", method = RequestMethod.POST)
  public BigDecimal rateRestaurant(@PathVariable Long id) {
    return restaurantService.rateRestaurant(id);
  }
  
  @ApiOperation(value = "Creates a meal.", tags = { SwaggerTags.TAG_MEAL })
  @RequestMapping(value = "/{id}/meals/create", method = RequestMethod.POST)
  public MealDto createMeal(@PathVariable Long id, @Valid @RequestBody MealDto mealDto) {
    return restaurantService.createMeal(id, mealDto);
  }
  
  @ApiOperation(value = "Updates a meal.", tags = { SwaggerTags.TAG_MEAL })
  @RequestMapping(value = "/{id}/meals/{mealId}", method = RequestMethod.PATCH)
  public MealDto updateMeal(@PathVariable Long id, @PathVariable Long mealId,
      @RequestBody MealDto mealDto) {
    return restaurantService.updateMeal(id, mealId, mealDto);
  }
  
  @ApiOperation(value = "Deletes a meal.", tags = { SwaggerTags.TAG_MEAL })
  @RequestMapping(value = "/{id}/meals/{mealId}", method = RequestMethod.DELETE)
  public void deleteMeal(@PathVariable Long id, @PathVariable Long mealId) {
    restaurantService.deleteMeal(id, mealId);
  }
  
}
