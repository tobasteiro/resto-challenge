package com.challenge.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.challenge.BaseIntegrationTest;
import com.challenge.dto.RateRestaurantResponseDto;
import com.challenge.dto.RestaurantDto;
import com.challenge.model.Restaurant;
import com.challenge.model.Review;
import com.fasterxml.jackson.core.type.TypeReference;

public class RestaurantControllerTest extends BaseIntegrationTest {

  @Value("classpath:mocks/restaurant/create-restaurant-request.json")
  private File createRestaurantRequest;

  @Value("classpath:mocks/restaurant/create-restaurant-response.json")
  private File createRestaurantResponse;

  @Value("classpath:mocks/restaurant/rate-restaurant-request.json")
  private File rateRestaurantRequest;

  @Test
  public void testCreateRestaurant() throws Exception {
    String resultString = mockMvc
        .perform(MockMvcRequestBuilders.post("/restaurants/")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(FileUtils.readFileToString(createRestaurantRequest)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .json(FileUtils.readFileToString(createRestaurantResponse)))
        .andReturn().getResponse().getContentAsString();

    RestaurantDto result = objectMapper.readValue(resultString, new TypeReference<RestaurantDto>() {
    });

    Restaurant restaurantDb = restaurantRepository.findOneById(result.getId()).get();

    assertNotNull(restaurantDb);
    assertEquals("Should save 2 meals", 2, restaurantDb.getMeals().size());
    assertEquals("Should save 1 review", 1, restaurantDb.getReviews().size());

    assertEquals("Should match address", result.getAddress(), restaurantDb.getAddress());
    assertEquals("Should match admin number", result.getAdminNumber(),
        restaurantDb.getAdminNumber());
    assertEquals("Should match commercial email", result.getCommercialEmail(),
        restaurantDb.getCommercialEmail());
    assertEquals("Should match commercial name", result.getCommercialName(),
        restaurantDb.getCommercialName());
    assertEquals("Should match legal name", result.getLegalName(), restaurantDb.getLegalName());
    assertEquals("Should match location", result.getLocation(), restaurantDb.getLocation());
    assertEquals("Should match logo", result.getLogoUrl(), restaurantDb.getLogo());
    assertEquals("Should match rating", result.getRating(), restaurantDb.getRating());
  }

  @Test
  public void testRateRestaurant() throws Exception {
    Long restaurantId = saveBasicRestaurant().getId();
    String resultString = mockMvc
        .perform(MockMvcRequestBuilders.post("/restaurants/" + restaurantId + "/rate")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(FileUtils.readFileToString(rateRestaurantRequest)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

    RateRestaurantResponseDto result = objectMapper.readValue(resultString,
        new TypeReference<RateRestaurantResponseDto>() {
        });
    
    assertEquals("Should return average rating",
        new BigDecimal(3.5).setScale(2, RoundingMode.HALF_UP), result.getTotalRating());
    
    Restaurant restaurant = restaurantRepository.findOneById(restaurantId).get();
    assertEquals("Should added review to restaurant", 2, restaurant.getReviews().size());

    Review review = reviewRepository.findOneById(result.getReviewDto().getReviewId()).get();
    assertEquals("Should save description from request", "desc rat", review.getDescription());
    assertEquals("Should save name from request", "name rat", review.getName());
    assertEquals("Should save rating from request", new Integer(5), review.getRating());
    
  }

}
