package com.challenge.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.dto.MealDto;
import com.challenge.dto.RateRestaurantResponseDto;
import com.challenge.dto.RestaurantBasicInformationDto;
import com.challenge.dto.RestaurantDto;
import com.challenge.dto.RestaurantFilterDto;
import com.challenge.dto.ReviewDto;
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
  public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
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
    } catch (UnexpectedRollbackException e) {
      LOG.error("Error creating restaurant: ", e);
      throw new RestaurantException(RestaurantError.DATABASE_ERROR);
    }
    return restaurantMapper.mapRestaurantInformation(restaurant);
  }

  @Override
  @Transactional(readOnly = true)
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
  @Transactional
  public void deleteRestaurant(Long id) {
    Optional<Restaurant> restaurant = restaurantRepository.findOneById(id);
    // For this challenge, I remove it physically, although for auditing purposes, it would be
    // better a logical removal.
    try {
      restaurantRepository.delete(restaurant
          .orElseThrow(() -> new RestaurantException(RestaurantError.RESTAURANT_NOT_FOUND)));
    } catch (Exception e) {
      LOG.error("Error deleting restaurant: ", e);
      throw new RestaurantException(RestaurantError.DATABASE_ERROR);
    }
  }

  @Override
  @Transactional
  public RestaurantDto updateRestaurant(Long id, RestaurantBasicInformationDto restaurantDto) {

    Restaurant restaurant = restaurantRepository.findById(id)
        .orElseThrow(() -> new RestaurantException(RestaurantError.RESTAURANT_NOT_FOUND));

    try {
      restaurantMapper.mapUpdatedRestaurant(restaurant, restaurantDto);
      restaurantRepository.save(restaurant);
    } catch (UnexpectedRollbackException e) {
      LOG.error("Error updating restaurant: ", e);
      throw new RestaurantException(RestaurantError.DATABASE_ERROR);
    }
    return restaurantMapper.mapRestaurantInformation(restaurant);
  }

  @Override
  @Transactional
  public RateRestaurantResponseDto rateRestaurant(Long restaurantId, ReviewDto reviewDto) {
    Restaurant restaurant = restaurantRepository.findOneById(restaurantId)
        .orElseThrow(() -> new RestaurantException(RestaurantError.RESTAURANT_NOT_FOUND));
    
    Review dbReview = restaurantMapper.mapDbReview(reviewDto, restaurant);

    restaurant.addReview(dbReview);
    
    BigDecimal rating = BigDecimal.ZERO;
    try {
      dbReview = reviewRepository.save(dbReview);
      rating = OperationUtils.calculateRating(restaurant.getReviews());
      restaurant.setRating(rating);
      restaurantRepository.save(restaurant);
    } catch(UnexpectedRollbackException e) {
      LOG.error("Error rating restaurant: ", e);
      throw new RestaurantException(RestaurantError.DATABASE_ERROR);
    }
    reviewDto.setReviewId(dbReview.getId());
    return restaurantMapper.mapRateRestaurantResponse(restaurant.getId(), reviewDto, rating);
  }

  @Override
  @Transactional
  public MealDto createMeal(Long restaurantId, @Valid MealDto mealDto) {
    Restaurant restaurant = restaurantRepository.findOneById(restaurantId)
        .orElseThrow(() -> new RestaurantException(RestaurantError.RESTAURANT_NOT_FOUND));

    try {
      Meal meal = restaurantMapper.mapDbMeal(mealDto, restaurant);
      meal = mealRepository.save(meal);
      restaurantRepository.save(restaurant);
      mealDto.setMealId(meal.getId());
    } catch (UnexpectedRollbackException e) {
      LOG.error("Error creating meal: ", e);
      throw new RestaurantException(RestaurantError.DATABASE_ERROR);
    }

    return mealDto;
  }

  @Override
  @Transactional
  public MealDto updateMeal(Long restaurantId, Long mealId, MealDto mealDto) {
    try {
      Meal meal = mealRepository.findOneByIdAndRestaurantId(mealId, restaurantId);
      restaurantMapper.mapMeal(meal, mealDto);
      mealRepository.save(meal);
    } catch (UnexpectedRollbackException e) {
      LOG.error("Error updating meal: ", e);
      throw new RestaurantException(RestaurantError.DATABASE_ERROR);
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
      throw new RestaurantException(RestaurantError.DATABASE_ERROR);
    }
  }

}
