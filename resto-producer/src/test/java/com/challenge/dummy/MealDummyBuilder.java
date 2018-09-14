package com.challenge.dummy;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.challenge.dto.MealDto;

public class MealDummyBuilder {

  public static MealDto createMealDtoDummy(Long mealId, BigDecimal price) {
    return new MealDto(mealId, "name ".concat(mealId.toString()),
        "description ".concat(mealId.toString()), price);
  }

  public static List<MealDto> createMealListDummy(List<BigDecimal> prices) {
    AtomicInteger index = new AtomicInteger();

    return prices.stream().map(p -> createMealDtoDummy((long) index.incrementAndGet(), p))
        .collect(Collectors.toList());
  }

}
