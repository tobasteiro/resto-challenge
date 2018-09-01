package com.challenge.service;

import com.challenge.dto.RestaurantDto;
import com.challenge.mapper.RestaurantMapper;
import com.challenge.model.Restaurant;
import com.challenge.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

  @Autowired
  private RestaurantRepository restaurantRepository;
  
  @Autowired
  private RestaurantMapper restaurantMapper;
  
  @Override
  public Long createRestaurant(RestaurantDto restaurantDto) {
    Restaurant restaurant = restaurantMapper.mapRestaurant(restaurantDto);
    return restaurantRepository.save(restaurant).getId();
  }

}
