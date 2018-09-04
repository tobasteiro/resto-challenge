package com.challenge.service;

import com.challenge.dto.OrderDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

  @Autowired
  private JavaMailSender emailSender;

  @Override
  @Async
  @Retryable
  public void sendOrderMail(String restaurantMail, OrderDto orderDto) {
    SimpleMailMessage message = new SimpleMailMessage(); 
    message.setTo(restaurantMail); 
    message.setSubject("New order created"); 
    String orderDetail = mapOrderDetail(orderDto);
    message.setText(orderDetail );
    emailSender.send(message);

  }

  private String mapOrderDetail(OrderDto orderDto) {
    StringBuilder sb = new StringBuilder();
    sb.append("Address: ");
    sb.append(orderDto.getAddress());
    sb.append("Phone number: ");
    sb.append(orderDto.getPhoneNumber());
    sb.append("Total Cost: $ ");
    sb.append(orderDto.getTotalCost());
    sb.append("Orderd Meals: ");
    //TODO return full meals
    for (Long meal : orderDto.getMeals()) {
      sb.append(meal);
      sb.append(", ");
    }
    return sb.toString();
  }

}
