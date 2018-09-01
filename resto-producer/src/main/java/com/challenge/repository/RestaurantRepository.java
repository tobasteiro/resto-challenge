package com.challenge.repository;

import com.challenge.model.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

  /**
   * @param restaurant id.
   * @return the restaurant selected.
   */
  Optional<Restaurant> findOneById(Long id);

  /**
   * @param ratingFrom filter.
   * @param ratingTo filter.
   * @return list of restaurants.
   */
  List<Restaurant> findByRatingBetween(BigDecimal ratingFrom, BigDecimal ratingTo);

  /**
   * @param ratingFrom filter.
   * @return restaurants with min rating.
   */
  List<Restaurant> findByRatingGreaterThanEqual(BigDecimal ratingFrom);

  /**
   * @param ratingto filter.
   * @return restaurants with max rating.
   */
  List<Restaurant> findByRatingLessThanEqual(BigDecimal ratingTo);

}
