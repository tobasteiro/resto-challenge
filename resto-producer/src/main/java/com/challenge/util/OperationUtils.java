package com.challenge.util;

import com.challenge.dto.ReviewDto;
import com.challenge.model.Meal;
import com.challenge.model.Review;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
    return new BigDecimal(doubleAverage);
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
    return new BigDecimal(doubleAverage);
  }

  /**
   * @param meals with prices.
   * @return calculated total amount.
   */
  public static BigDecimal calculateMealsTotalPrice(List<Meal> meals) {
    if (Objects.isNull(meals)) {
      return null;
    }
    return meals.stream().map(m -> m.getPrice()).reduce(BigDecimal.ZERO, (p, q) -> p.add(q));
  }
}
