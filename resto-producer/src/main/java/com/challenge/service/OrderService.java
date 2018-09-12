package com.challenge.service;

import javax.validation.Valid;

import com.challenge.dto.OrderRequestDto;
import com.challenge.dto.OrderResponseDto;

public interface OrderService {

  /**
   * Creates a new order.
   * 
   * @param orderRequestDto information.
   * @param restaurantId.
   * @return new order with ETA (estimated time of arrival).
   */
  OrderResponseDto createOrder(@Valid OrderRequestDto orderRequestDto, Long restaurantId);

}
