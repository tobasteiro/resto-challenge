package com.challenge.dummy;

import java.util.List;
import java.util.stream.Collectors;

import com.challenge.dto.ReviewDto;
import com.challenge.model.Review;

public class ReviewDummyBuilder {

  public static ReviewDto createReviewDtoDummy(Integer rating) {
    return new ReviewDto("name ".concat(rating.toString()),
        "description ".concat(rating.toString()), rating);
  }

  public static List<ReviewDto> createReviewDtoListDummy(List<Integer> ratings) {
    return ratings.stream().map(r -> createReviewDtoDummy(r)).collect(Collectors.toList());
  }

  private static Review createReviewDummy(Integer rating) {
    return new Review("name ".concat(rating.toString()), "description ".concat(rating.toString()),
        rating, null);
  }

  public static List<Review> createReviewListDummy(List<Integer> ratings) {
    return ratings.stream().map(r -> createReviewDummy(r)).collect(Collectors.toList());
  }

}
