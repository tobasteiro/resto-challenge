package com.challenge.mapper;

import com.challenge.dto.LocationDto;
import com.challenge.dto.MealDto;
import com.challenge.dto.RestaurantBasicInformationDto;
import com.challenge.dto.RestaurantDto;
import com.challenge.dto.ReviewDto;
import com.challenge.model.Meal;
import com.challenge.model.Restaurant;
import com.challenge.model.Review;
import com.challenge.util.LocationUtils;
import com.challenge.util.OperationUtils;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

  /**
   * Maps entity restaurant.
   * 
   * @param restaurantDto information.
   * @return mapped restaurant.
   */
  public Restaurant mapDbRestaurant(RestaurantBasicInformationDto restaurantDto) {
    Restaurant restaurant = new Restaurant();
    
    restaurant.setAddress(restaurantDto.getAddress());
    restaurant.setAdminNumber(restaurantDto.getAdminNumber());
    restaurant.setCommercialEmail(restaurantDto.getCommercialEmail());
    restaurant.setCommercialName(restaurantDto.getCommercialName());
    restaurant.setLegalName(restaurantDto.getLegalName());
    restaurant.setLocation(LocationUtils.createLocation(restaurantDto.getLocation()));
    restaurant.setLogo(restaurantDto.getLogoUrl());
    restaurant.setRating(restaurantDto.getRating());
    
    return restaurant;
  }

  /**
   * @param mealsDto. 
   * @param restaurant information.
   * @return list of meals.
   */
  public List<Meal> mapDbMeals(List<MealDto> mealsDto, Restaurant restaurant) {
    if (Objects.isNull(mealsDto) || mealsDto.isEmpty()) {
      return null;
    }
    List<Meal> meals = mealsDto.stream()
        .map(m -> new Meal(m.getMealName(), m.getMealDescription(), m.getMealPrice(), restaurant))
        .collect(Collectors.toList());
    restaurant.setMeals(meals);
    return meals;
  }
  
  /**
   * @param mealDto. 
   * @param restaurant information.
   * @return mapped meal.
   */
  public Meal mapDbMeal(MealDto mealDto, Restaurant restaurant) {
    return mapDbMeals(Arrays.asList(mealDto), restaurant).stream().findFirst().orElse(null);
  }


  /**
   * @param reviewsDto.
   * @param restaurant information.
   * @return list of reviews.
   */
  public List<Review> mapDbReviews(List<ReviewDto> reviewsDto, Restaurant restaurant) {
    if (Objects.isNull(reviewsDto) || reviewsDto.isEmpty()) {
      return null;
    }
    List<Review> reviews = reviewsDto.stream()
        .map(
            r -> new Review(r.getReviewName(), r.getReviewDescription(), r.getRating(), restaurant))
        .collect(Collectors.toList());
    restaurant.setReviews(reviews);

    restaurant.setRating(OperationUtils.calculateRatingDto(reviewsDto));
    return reviews;
  }

  /**
   * @param restaurants from database.
   * @return mapped restaurants response.
   */
  public List<RestaurantDto> mapRestaurantResponse(List<Restaurant> restaurants) {
    return restaurants.stream().map(r -> mapRestaurantInformation(r)).collect(Collectors.toList());
  }

  
  /**
   * @param restaurant information.
   * @return mapped restaurant info.
   */
  public RestaurantDto mapRestaurantInformation(Restaurant restaurant) {
    return RestaurantDto.builder().with(b -> {
      b.address = restaurant.getAddress();
      b.adminNumber = restaurant.getAdminNumber();
      b.commercialEmail = restaurant.getCommercialEmail();
      b.commercialName = restaurant.getCommercialName();
      b.legalName = restaurant.getLegalName();
      b.location = LocationUtils.createLocation(restaurant.getLocation());
      b.logoUrl = restaurant.getLogo();
      b.rating = restaurant.getRating();
      b.meals = mapMealsInformation(restaurant.getMeals());
      b.reviews = mapReviewsInformation(restaurant.getReviews());
    }).build();
  }

  private List<ReviewDto> mapReviewsInformation(List<Review> reviews) {
    if (Objects.isNull(reviews) || reviews.isEmpty()) {
      return null;
    }
    return reviews.stream().map(r -> new ReviewDto(r.getName(), r.getDescription(), r.getRating()))
        .collect(Collectors.toList());
  }

  private List<MealDto> mapMealsInformation(List<Meal> meals) {
    if (Objects.isNull(meals) || meals.isEmpty()) {
      return null;
    }
    return meals.stream()
        .map(m -> new MealDto(m.getId(), m.getName(), m.getDescription(), m.getPrice()))
        .collect(Collectors.toList());
  }

  /**
   * Populates updated restaurant.
   * @param restaurant to update.
   * @param restaurantDto information.
   */
  public void mapUpdatedRestaurant(Restaurant restaurant,
      RestaurantBasicInformationDto restaurantDto) {
    String address = restaurantDto.getAddress();
    String adminNumber = restaurantDto.getAdminNumber();
    String commercialEmail = restaurantDto.getCommercialEmail();
    String commercialName = restaurantDto.getCommercialName();
    String legalName = restaurantDto.getLegalName();
    LocationDto location = restaurantDto.getLocation();
    String logoUrl = restaurantDto.getLogoUrl();
    
    if (Objects.nonNull(address)) {
      restaurant.setAddress(address);
    }
    if (Objects.nonNull(adminNumber)) {
      restaurant.setAdminNumber(adminNumber);
    }
    if (Objects.nonNull(commercialEmail)) {
      restaurant.setCommercialEmail(commercialEmail);
    }
    if (Objects.nonNull(commercialName)) {
      restaurant.setCommercialName(commercialName);
    }
    if (Objects.nonNull(legalName)) {
      restaurant.setLegalName(legalName);
    }
    if (Objects.nonNull(location)) {
      restaurant.setLocation(LocationUtils.createLocation(location));
    }
    if (Objects.nonNull(logoUrl)) {
      restaurant.setLogo(logoUrl);
    }
  }

  /**
   * @param meal db.
   * @param mealDto info.
   */
  public void mapMeal(Meal meal, MealDto mealDto) {
    if (Objects.nonNull(mealDto.getMealDescription())) {
      meal.setDescription(mealDto.getMealDescription());
    }

    if (Objects.nonNull(mealDto.getMealName())) {
      meal.setName(mealDto.getMealName());
    }

    if (Objects.nonNull(mealDto.getMealPrice())) {
      meal.setPrice(mealDto.getMealPrice());
    }
  }

}
