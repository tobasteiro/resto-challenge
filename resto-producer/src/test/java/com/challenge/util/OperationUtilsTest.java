package com.challenge.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.challenge.dto.MealDto;
import com.challenge.dto.ReviewDto;
import com.challenge.dummy.MealDummyBuilder;
import com.challenge.dummy.ReviewDummyBuilder;
import com.challenge.model.Review;

public class OperationUtilsTest {

  @Test
  public void testCalculateRatingDto() {
    List<ReviewDto> reviewsDto = ReviewDummyBuilder
        .createReviewDtoListDummy(Arrays.asList(1, 3, 3, 4, 5, 3));
    BigDecimal result = OperationUtils.calculateRatingDto(reviewsDto);

    assertEquals("Should return average rating", BigDecimal.valueOf(3.17), result);
  }

  @Test
  public void testCalculateRatingDb() {
    List<Review> reviews = ReviewDummyBuilder
        .createReviewListDummy(Arrays.asList(1, 3, 3, 4, 5, 3));
    BigDecimal result = OperationUtils.calculateRating(reviews);

    assertEquals("Should return average rating", BigDecimal.valueOf(3.17), result);
  }

  @Test
  public void testCalculateMealsTotalPrice() {
    List<MealDto> meals = MealDummyBuilder
        .createMealListDummy(Arrays.asList(new BigDecimal(10.3), new BigDecimal(46.88)));
    BigDecimal result = OperationUtils.calculateMealsTotalPrice(meals);

    assertEquals("Should return meals total price", BigDecimal.valueOf(57.18), result);
  }
}
