package com.challenge.mapper;

import com.challenge.dto.OrderDto;
import com.challenge.dto.OrderResponseDto;
import com.challenge.model.Order;
import com.challenge.model.Restaurant;
import com.challenge.util.LocationUtils;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

  /**
   * @param restaurant.
   * @param orderDto.
   * @return mapped db order.
   */
  public Order mapDbOder(OrderDto orderDto, Restaurant restaurant) {
    Order order = new Order();
    order.setAddress(orderDto.getAddress());
    order.setLocation(LocationUtils.createLocation(orderDto.getLocation()));

    restaurant.getMeals().stream().filter(m -> orderDto.getMeals().contains(m.getId()))
        .forEach(m -> order.addMeal(m));
    order.setTotalCost(orderDto.getTotalCost());
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

}
