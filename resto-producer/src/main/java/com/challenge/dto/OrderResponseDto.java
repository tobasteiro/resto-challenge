package com.challenge.dto;

public class OrderResponseDto {

  private OrderDto orderDto;

  private String estimatedArrivalTime;

  public OrderResponseDto() {
    super();
  }

  public OrderResponseDto(OrderDto orderDto, String estimatedArrivalTime) {
    super();
    this.orderDto = orderDto;
    this.estimatedArrivalTime = estimatedArrivalTime;
  }

  public OrderDto getOrderDto() {
    return orderDto;
  }

  public String getEstimatedArrivalTime() {
    return estimatedArrivalTime;
  }

}
