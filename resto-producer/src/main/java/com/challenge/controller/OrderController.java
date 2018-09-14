package com.challenge.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.dto.OrderRequestDto;
import com.challenge.dto.OrderResponseDto;
import com.challenge.service.OrderService;
import com.challenge.util.SwaggerTags;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/restaurants")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @ApiOperation(value = "Creates an order.", tags = { SwaggerTags.TAG_ORDER })
  @RequestMapping(value = "/{restaurantId}/orders/", method = RequestMethod.POST)
  public OrderResponseDto createOrder(@RequestBody @Valid OrderRequestDto orderRequestDto,
      @PathVariable Long restaurantId) {
    return orderService.createOrder(orderRequestDto, restaurantId);
  }

}
