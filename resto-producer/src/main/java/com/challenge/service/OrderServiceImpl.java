package com.challenge.service;

import com.challenge.dto.OrderDto;
import com.challenge.dto.OrderResponseDto;
import com.challenge.enumeration.RestaurantError;
import com.challenge.exception.RestaurantException;
import com.challenge.facade.GeoLocationFacade;
import com.challenge.mapper.OrderMapper;
import com.challenge.model.Meal;
import com.challenge.model.Order;
import com.challenge.model.Restaurant;
import com.challenge.producer.MessageSender;
import com.challenge.repository.OrderRepository;
import com.challenge.repository.RestaurantRepository;
import com.challenge.util.OperationUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

  private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderMapper orderMapper;

  @Autowired
  private GeoLocationFacade geoLocationFacade;

  @Autowired
  private MessageSender<OrderDto> messageSender;
  
  @Value("${kafka.topic.order}")
  private String orderTopic;

  @Override
  @Transactional
  public OrderResponseDto createOrder(OrderDto orderDto, Long restaurantId) {

    Restaurant restaurant = restaurantRepository.findOneById(restaurantId)
        .orElseThrow(() -> new RestaurantException(RestaurantError.RESTAURANT_NOT_FOUND));

    validateTotalCost(orderDto, restaurant.getMeals());

    Order order = orderMapper.mapDbOder(orderDto, restaurant);
    // Save order
    orderRepository.save(order);

    try {
      // Send kafka message notification
      messageSender.send(orderDto, orderTopic);

      // Calculate ETA with google maps api
      String calculatedETA = geoLocationFacade.calculateETA(order.getLocation().getLocation(),
          restaurant.getLocation().getLocation());

      return orderMapper.mapOrderResponse(orderDto, calculatedETA);
    } catch (Exception e) {
      LOG.error("Error creating order: ", e);
      // TODO throw custom exception
    }

    return null;
  }

  private void validateTotalCost(OrderDto orderDto, List<Meal> meals) {
    BigDecimal totalCost = orderDto.getTotalCost();

    BigDecimal calculatedCost = OperationUtils.calculateMealsTotalPrice(meals);

    // Total amount should be greater or equal than meal prices sum.
    if (totalCost.compareTo(calculatedCost) < 0) {
      throw new RestaurantException(RestaurantError.INVALID_TOTAL_AMOUNT);
    }
  }

}
