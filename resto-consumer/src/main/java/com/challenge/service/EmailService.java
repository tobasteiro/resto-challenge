package com.challenge.service;

import com.challenge.dto.OrderDto;

public interface EmailService {

  /**
   * Sends an email notifying the restaurant a new order was created.
   * 
   * @param orderDto information.
   * @param restaurantMail.
   */
  void sendOrderMail(String restaurantMail, OrderDto orderDto);

}
