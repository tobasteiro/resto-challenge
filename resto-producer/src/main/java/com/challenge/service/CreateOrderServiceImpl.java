package com.challenge.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.dto.MealDto;
import com.challenge.dto.OrderDto;
import com.challenge.dto.OrderRequestDto;
import com.challenge.enumeration.RestaurantError;
import com.challenge.exception.OrderException;
import com.challenge.exception.RestaurantException;
import com.challenge.mapper.OrderMapper;
import com.challenge.mapper.RestaurantMapper;
import com.challenge.model.Meal;
import com.challenge.model.Order;
import com.challenge.model.Restaurant;
import com.challenge.repository.MealRepository;
import com.challenge.repository.OrderRepository;
import com.challenge.repository.RestaurantRepository;
import com.challenge.util.OperationUtils;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private MealRepository mealRepository;

  @Autowired
  private RestaurantMapper restaurantMapper;

  @Autowired
  private OrderMapper orderMapper;

  @Autowired
  private OrderRepository orderRepository;

  @Override
  @Transactional
  public OrderDto createDbOrder(Long restaurantId, OrderRequestDto orderRequestDto) {
    Restaurant restaurant = restaurantRepository.findOneById(restaurantId)
        .orElseThrow(() -> new RestaurantException(RestaurantError.RESTAURANT_NOT_FOUND));

    List<Meal> mealsInformation = mealRepository.findByIdIn(orderRequestDto.getMeals());

    List<MealDto> mealsDto = restaurantMapper.mapMealsInformation(mealsInformation);
    OrderDto orderDto = orderMapper.mapOrderMealsInformation(orderRequestDto, mealsDto, restaurant.getLocation());

    Order order = orderMapper.mapDbOder(orderRequestDto, restaurant);

    validateTotalCost(orderDto);

    try {
      // Save order
      orderRepository.save(order);
    } catch (UnexpectedRollbackException e) {
      throw new OrderException(RestaurantError.DATABASE_ERROR);
    }
    
    return orderDto;

  }

  private void validateTotalCost(OrderDto orderDto) {
    BigDecimal totalCost = orderDto.getTotalCost();

    BigDecimal calculatedCost = OperationUtils.calculateMealsTotalPrice(orderDto.getMeals());

    // Total amount should be greater or equal than meal prices sum.
    if (totalCost.compareTo(calculatedCost) < 0) {
      throw new RestaurantException(RestaurantError.INVALID_TOTAL_AMOUNT);
    }
  }

}
