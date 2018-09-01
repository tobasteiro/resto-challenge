package com.challenge.controller;

import com.challenge.dto.RestaurantDto;
import com.challenge.service.RestaurantService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
  
  @Autowired
  private RestaurantService restaurantService;

  @ApiOperation(value = "Creates a restaurant.", tags = { "Restaurant" })
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public Long createRestaurant(@RequestBody RestaurantDto restaurantDto) {
    return restaurantService.createRestaurant(restaurantDto);
  }

  @ApiOperation(value = "Return the list of restaurants.", tags = { "Restaurant" })
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String listRestaurants() {
    return "Restaurants";
  }
  
}
