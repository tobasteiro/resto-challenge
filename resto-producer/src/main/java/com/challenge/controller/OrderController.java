package com.challenge.controller;

import com.challenge.dto.OrderDto;
import com.challenge.dto.OrderResponseDto;
import com.challenge.service.OrderService;
import com.challenge.util.SwaggerTags;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurants/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @ApiOperation(value = "Creates an order.", tags = { SwaggerTags.TAG_ORDER })
  @RequestMapping(value = "/restaurants/{restaurantId}/create", method = RequestMethod.POST)
  public OrderResponseDto createMeal(@RequestBody @Valid OrderDto orderDto,
      @PathVariable Long restaurantId) {
    return orderService.createOrder(orderDto, restaurantId);
  }

}
