package com.challenge.service;

import com.challenge.dto.MealDto;
import com.challenge.dto.RestaurantBasicInformationDto;
import com.challenge.dto.RestaurantDto;
import com.challenge.dto.RestaurantFilterDto;
import com.challenge.enumeration.RestaurantError;
import com.challenge.exception.RestaurantException;
import com.challenge.mapper.RestaurantMapper;
import com.challenge.model.Meal;
import com.challenge.model.Restaurant;
import com.challenge.model.Review;
import com.challenge.repository.MealRepository;
import com.challenge.repository.RestaurantRepository;
import com.challenge.repository.ReviewRepository;
import com.challenge.util.OperationUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
public class RestaurantServiceImpl implements RestaurantService {

  private static final Logger LOG = LoggerFactory.getLogger(RestaurantServiceImpl.class);

  @Autowired
  private RestaurantRepository restaurantRepository;
  
  @Autowired
  private RestaurantMapper restaurantMapper;

  @Autowired
  private MealRepository mealRepository;
  
  @Autowired
  private ReviewRepository reviewRepository;
  
  @Override
  @Transactional
  public Long createRestaurant(RestaurantDto restaurantDto) {
    Restaurant restaurant = restaurantMapper.mapDbRestaurant(restaurantDto);
    
    try {
      restaurant = restaurantRepository.save(restaurant);
      
      List<Meal> mapMeals = restaurantMapper.mapDbMeals(restaurantDto.getMeals(), restaurant);
      if (Objects.nonNull(mapMeals) && !mapMeals.isEmpty()) {
        mealRepository.saveAll(mapMeals);
      }
      List<Review> mapReviews = restaurantMapper.mapDbReviews(restaurantDto.getReviews(), restaurant);
      if (Objects.nonNull(mapReviews) && !mapReviews.isEmpty()) {
        reviewRepository.saveAll(mapReviews);
      }
    } catch (Exception e) {
      LOG.error("Error creating restaurant: ", e);
      //TODO throw custom exception
    }
    return restaurant.getId();
  }

  @Override
  public List<RestaurantDto> listRestaurants(RestaurantFilterDto restaurantFilterDto) {
    
    List<Restaurant> restaurants = null;
    if (Objects.nonNull(restaurantFilterDto.getRatingFrom())
        && Objects.nonNull(restaurantFilterDto.getRatingTo())) {
      restaurants = restaurantRepository.findByRatingBetween(restaurantFilterDto.getRatingFrom(),
          restaurantFilterDto.getRatingTo());
    } else if (Objects.nonNull(restaurantFilterDto.getRatingFrom())){
      restaurants = restaurantRepository.findByRatingGreaterThanEqual(restaurantFilterDto.getRatingFrom());
    } else if (Objects.nonNull(restaurantFilterDto.getRatingTo())) {
      restaurants = restaurantRepository.findByRatingLessThanEqual(restaurantFilterDto.getRatingTo());
    } else {
      restaurants = restaurantRepository.findAll();
    }
    return restaurantMapper.mapRestaurantResponse(restaurants);
  }

  @Override
  public void deleteRestaurant(Long id) {
    Optional<Restaurant> restaurant = restaurantRepository.findOneById(id);
    // For this challenge, I remove it physically, although for auditing purposes, it would be
    // better a logical removal.
    restaurantRepository.delete(restaurant
        .orElseThrow(() -> new RestaurantException(RestaurantError.RESTAURANT_NOT_FOUND)));
  }

  @Override
  @Transactional
  public RestaurantDto updateRestaurant(Long id, RestaurantBasicInformationDto restaurantDto) {

    Restaurant restaurant = restaurantRepository.findById(id)
        .orElseThrow(() -> new RestaurantException(RestaurantError.RESTAURANT_NOT_FOUND));

    try {
      restaurantMapper.mapUpdatedRestaurant(restaurant, restaurantDto);
      restaurantRepository.save(restaurant);
    } catch (Exception e) {
      LOG.error("Error updating restaurant: ", e);
      // TODO throw custom exception
    }
    return restaurantMapper.mapRestaurantInformation(restaurant);
  }

  @Override
  public BigDecimal rateRestaurant(Long id) {
    Restaurant restaurant = restaurantRepository.findOneById(id)
        .orElseThrow(() -> new RestaurantException(RestaurantError.RESTAURANT_NOT_FOUND));
    
    return OperationUtils.calculateRating(restaurant.getReviews());
  }

  @Override
  @Transactional
  public MealDto createMeal(Long restaurantId, @Valid MealDto mealDto) {
    Restaurant restaurant = restaurantRepository.findOneById(restaurantId)
        .orElseThrow(() -> new RestaurantException(RestaurantError.RESTAURANT_NOT_FOUND));

    try {
      Meal meal = restaurantMapper.mapDbMeal(mealDto, restaurant);
      mealRepository.save(meal);
      restaurantRepository.save(restaurant);
    } catch (Exception e) {
      LOG.error("Error deleting meal: ", e);
      //TODO throw custom exception
    }
    
    return null;
  }

  @Override
  public MealDto updateMeal(Long restaurantId, Long mealId, MealDto mealDto) {
    try {
      Meal meal = mealRepository.findOneByIdAndRestaurantId(mealId, restaurantId);
      restaurantMapper.mapMeal(meal, mealDto);
      mealRepository.save(meal);
    } catch (Exception e) {
      LOG.error("Error deleting meal: ", e);
      // TODO throw custom exception
    }
    
    return mealDto;
  }

  @Override
  @Transactional
  public void deleteMeal(Long restaurantId, Long mealId) {
    try {
      Meal meal = mealRepository.findOneByIdAndRestaurantId(mealId, restaurantId);
      mealRepository.delete(meal);
    } catch (Exception e) {
      LOG.error("Error deleting meal: ", e);
      // TODO throw custom exception
    }
  }

}
