package com.challenge.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

import com.challenge.dto.MealDto;
import com.challenge.dto.ReviewDto;
import com.challenge.model.Review;

public class OperationUtils {

  /**
   * @param reviewsDto.
   * @return average calculated rating.
   */
  public static BigDecimal calculateRatingDto(List<ReviewDto> reviewsDto) {
    if (Objects.isNull(reviewsDto)) {
      return null;
    }
    double doubleAverage = reviewsDto.stream().mapToDouble(ReviewDto::getRating).average()
        .orElse(Double.NaN);
    return new BigDecimal(doubleAverage).setScale(2, RoundingMode.HALF_UP);
  }

  /**
   * @param reviews.
   * @return average calculated rating.
   */
  public static BigDecimal calculateRating(List<Review> reviews) {
    if (Objects.isNull(reviews)) {
      return null;
    }
    double doubleAverage = reviews.stream().mapToDouble(Review::getRating).average()
        .orElse(Double.NaN);
    return new BigDecimal(doubleAverage).setScale(2, RoundingMode.HALF_UP);
  }

  /**
   * @param meals with prices.
   * @return calculated total amount.
   */
  public static BigDecimal calculateMealsTotalPrice(List<MealDto> meals) {
    if (Objects.isNull(meals)) {
      return null;
    }
    return meals.stream().map(m -> m.getMealPrice()).reduce(BigDecimal.ZERO, (p, q) -> p.add(q))
        .setScale(2, RoundingMode.HALF_UP);
  }
}
