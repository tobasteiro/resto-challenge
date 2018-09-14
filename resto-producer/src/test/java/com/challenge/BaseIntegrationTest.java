package com.challenge;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.challenge.model.Location;
import com.challenge.model.Meal;
import com.challenge.model.Restaurant;
import com.challenge.model.Review;
import com.challenge.repository.MealRepository;
import com.challenge.repository.RestaurantRepository;
import com.challenge.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

  @Autowired
  private WebApplicationContext context;

  protected MockMvc mockMvc;

  @Autowired
  protected RestaurantRepository restaurantRepository;

  @Autowired
  protected MealRepository mealRepository;

  @Autowired
  protected ReviewRepository reviewRepository;

  @Autowired
  protected ObjectMapper objectMapper;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  protected Restaurant saveBasicRestaurant() {
    Restaurant restaurant = new Restaurant();
    restaurant.setAddress("address");
    restaurant.setAdminNumber("adminNumber");
    restaurant.setCommercialEmail("commercialEmail");
    restaurant.setCommercialName("commercialName");
    restaurant.setLegalName("legalName");
    restaurant.setLocation(new Location(-32.914459, -68.858399));
    restaurant.setLogo("http://logourl.com");
    restaurant.setRating(BigDecimal.ONE);
    restaurant = restaurantRepository.save(restaurant);
    restaurant.setMeals(new ArrayList<Meal>(Arrays.asList(saveBasicMeal(restaurant))));
    restaurant.setReviews(new ArrayList<Review>(Arrays.asList(saveBasicReview(restaurant))));
    return restaurant;
  }

  protected Review saveBasicReview(Restaurant restaurant) {
    Review review = new Review("name", "description", 2, restaurant);
    return reviewRepository.save(review);
  }

  protected Meal saveBasicMeal(Restaurant restaurant) {
    Meal meal = new Meal("name", "description", new BigDecimal(10), restaurant);
    return mealRepository.save(meal);
  }
}
