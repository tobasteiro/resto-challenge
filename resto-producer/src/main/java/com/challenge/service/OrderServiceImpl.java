package com.challenge.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.dto.MealDto;
import com.challenge.dto.OrderDto;
import com.challenge.dto.OrderRequestDto;
import com.challenge.dto.OrderResponseDto;
import com.challenge.enumeration.RestaurantError;
import com.challenge.exception.GeolocationException;
import com.challenge.exception.KafkaException;
import com.challenge.exception.OrderException;
import com.challenge.exception.RestaurantException;
import com.challenge.facade.GeoLocationFacade;
import com.challenge.mapper.OrderMapper;
import com.challenge.mapper.RestaurantMapper;
import com.challenge.model.Meal;
import com.challenge.model.Order;
import com.challenge.model.Restaurant;
import com.challenge.producer.MessageSender;
import com.challenge.repository.MealRepository;
import com.challenge.repository.OrderRepository;
import com.challenge.repository.RestaurantRepository;
import com.challenge.util.OperationUtils;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private MealRepository mealRepository;

  @Autowired
  private OrderMapper orderMapper;
  
  @Autowired
  private RestaurantMapper restaurantMapper;

  @Autowired
  private GeoLocationFacade geoLocationFacade;

  @Autowired
  private MessageSender<OrderDto> messageSender;

  @Value("${kafka.topic.order}")
  private String orderTopic;

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public OrderResponseDto createOrder(OrderRequestDto orderRequestDto, Long restaurantId) {
    Restaurant restaurant = restaurantRepository.findOneById(restaurantId)
        .orElseThrow(() -> new RestaurantException(RestaurantError.RESTAURANT_NOT_FOUND));

    List<Meal> mealsInformation = mealRepository.findByIdIn(orderRequestDto.getMeals());

    List<MealDto> mealsDto = restaurantMapper.mapMealsInformation(mealsInformation);
    OrderDto orderDto = orderMapper.mapOrderMealsInformation(orderRequestDto, mealsDto);
    validateTotalCost(orderDto);

    Order order = orderMapper.mapDbOder(orderRequestDto, restaurant);

    try {
      // Save order
      orderRepository.save(order);
    } catch (UnexpectedRollbackException e) {
      throw new OrderException(RestaurantError.DATABASE_ERROR);
    }

    try {
      // Send kafka message notification
      messageSender.send(orderDto, orderTopic);

      // Calculate ETA with google maps api
      String calculatedETA = geoLocationFacade.calculateETA(order.getLocation(),
          restaurant.getLocation());

      return orderMapper.mapOrderResponse(orderDto, calculatedETA);
    } catch (KafkaException e) {
      throw new OrderException(RestaurantError.ERROR_SENDING_MESSAGE);
    } catch (GeolocationException e) {
      throw new OrderException(RestaurantError.GEOLOCATION_API_ERROR);
    }
  }

  private void validateTotalCost(OrderDto orderDto) {
    BigDecimal totalCost = orderDto.getTotalCost();

    BigDecimal calculatedCost = OperationUtils.calculateMealsTotalPrice(orderDto.getMeals());

    // Total amount should be greater or equal than meal prices sum.
    if (totalCost.compareTo(calculatedCost) < 0) {
      throw new RestaurantException(RestaurantError.INVALID_TOTAL_AMOUNT);
    }
  }

}
