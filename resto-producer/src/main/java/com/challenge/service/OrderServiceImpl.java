package com.challenge.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.challenge.dto.OrderDto;
import com.challenge.dto.OrderRequestDto;
import com.challenge.dto.OrderResponseDto;
import com.challenge.enumeration.RestaurantError;
import com.challenge.exception.GeolocationException;
import com.challenge.exception.KafkaException;
import com.challenge.exception.OrderException;
import com.challenge.facade.GeoLocationFacade;
import com.challenge.mapper.OrderMapper;
import com.challenge.producer.MessageSender;

@Service
public class OrderServiceImpl implements OrderService {

  private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

  @Autowired
  private CreateOrderService createOrderService;

  @Autowired
  private OrderMapper orderMapper;
  
  @Autowired
  private GeoLocationFacade geoLocationFacade;

  @Autowired
  private MessageSender<OrderDto> messageSender;

  @Value("${kafka.topic.order}")
  private String orderTopic;

  @Override
  public OrderResponseDto createOrder(OrderRequestDto orderRequestDto, Long restaurantId) {

    OrderDto orderDto = createOrderService.createDbOrder(restaurantId, orderRequestDto);
    
    try {
      // Send kafka message notification
      messageSender.send(orderDto, orderTopic);

      // Calculate ETA with google maps api
      String calculatedETA = geoLocationFacade.calculateETA(orderDto.getLocation(),
          orderDto.getRestaurantLocation());

      return orderMapper.mapOrderResponse(orderDto, calculatedETA);
    } catch (KafkaException e) {
      throw new OrderException(RestaurantError.ERROR_SENDING_MESSAGE);
    } catch (GeolocationException e) {
      LOG.error("Error calling Google Maps API: ", e);
      // If there is an error with google maps API, it shouldn't fail.
      return orderMapper.mapOrderResponse(orderDto, "N/A");
    } catch (Exception e) {
      throw new OrderException(RestaurantError.ORDER_CREATION_ERROR);
    }
  }

}
