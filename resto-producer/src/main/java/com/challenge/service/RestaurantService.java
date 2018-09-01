package com.challenge.service;

import com.challenge.dto.RestaurantDto;

public interface RestaurantService {

  /**
   * @param restaurantDto information.
   * @return restaurant generated id.
   */
  Long createRestaurant(RestaurantDto restaurantDto);

}
