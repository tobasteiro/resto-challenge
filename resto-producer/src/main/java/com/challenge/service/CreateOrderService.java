package com.challenge.service;

import com.challenge.dto.OrderDto;
import com.challenge.dto.OrderRequestDto;

public interface CreateOrderService {

  /**
   * Creates the order in database.
   * 
   * @param restaurantId.
   * @param orderRequestDto information.
   * @return OrderDto.
   */
  OrderDto createDbOrder(Long restaurantId, OrderRequestDto orderRequestDto);
}
