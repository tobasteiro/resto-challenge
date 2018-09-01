package com.challenge.mapper;

import com.challenge.dto.RestaurantDto;
import com.challenge.model.Location;
import com.challenge.model.Restaurant;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RestaurantMapper {

  /**
   * @param restaurantDto information.
   * @return mapped restaurant.
   */
  public Restaurant mapRestaurant(RestaurantDto restaurantDto) {
    Restaurant restaurant = new Restaurant();
    
    restaurant.setAddress("address");
    restaurant.setAdminNumber("adminNumber");
    restaurant.setCommercialEmail("commercialEmail");
    restaurant.setCommercialName("commercialName");
    restaurant.setLegalName("legalName");
    restaurant.setLocation(new Location(1D, 1D));
    restaurant.setLogo("logo");
    restaurant.setRating(BigDecimal.ONE);
    restaurant.setMeals(null);
    restaurant.setReviews(null);

    return restaurant;
  }

}
