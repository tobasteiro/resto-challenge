package com.challenge.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.challenge.dto.MealDto;
import com.challenge.dto.OrderDto;
import com.challenge.dto.OrderRequestDto;
import com.challenge.dto.OrderResponseDto;
import com.challenge.model.Order;
import com.challenge.model.Restaurant;
import com.challenge.util.LocationUtils;

@Component
public class OrderMapper {

  /**
   * @param restaurant.
   * @param orderDto.
   * @return mapped db order.
   */
  public Order mapDbOder(OrderRequestDto orderRequestDto, Restaurant restaurant) {
    Order order = new Order();
    order.setAddress(orderRequestDto.getAddress());
    order.setLocation(LocationUtils.createLocation(orderRequestDto.getLocation()));

    restaurant.getMeals().stream().filter(m -> orderRequestDto.getMeals().contains(m.getId()))
        .forEach(m -> order.addMeal(m));
    order.setTotalCost(orderRequestDto.getTotalCost());
    return order;
  }

  /**
   * @param orderDto.
   * @param calculatedETA.
   * @return mapped response.
   */
  public OrderResponseDto mapOrderResponse(OrderDto orderDto, String calculatedETA) {
    return new OrderResponseDto(orderDto, calculatedETA);
  }

  /**
   * @param orderRequestDto.
   * @param mealsInformation.
   * @return order dto with meal info.
   */
  public OrderDto mapOrderMealsInformation(OrderRequestDto orderRequestDto,
      List<MealDto> mealsInformation) {
    return new OrderDto(mealsInformation, orderRequestDto.getTotalCost(),
        orderRequestDto.getAddress(), orderRequestDto.getLocation(),
        orderRequestDto.getRestaurantMail(), orderRequestDto.getPhoneNumber());
  }

}
