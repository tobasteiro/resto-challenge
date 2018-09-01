package com.challenge.service;

import com.challenge.dto.OrderDto;
import com.challenge.dto.OrderResponseDto;

import javax.validation.Valid;

public interface OrderService {

  /**
   * Creates a new order.
   * 
   * @param orderDto information.
   * @param restaurantId.
   * @return new order with ETA (estimated time of arrival).
   */
  OrderResponseDto createOrder(@Valid OrderDto orderDto, Long restaurantId);

}
