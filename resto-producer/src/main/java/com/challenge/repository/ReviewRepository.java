package com.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  /**
   * @param id review.
   * @return review by id.
   */
  public Optional<Review> findOneById(Long id);
}
