package com.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.challenge.dto.MealDto;
import com.challenge.dto.OrderDto;

@Component
public class EmailServiceImpl implements EmailService {

  @Autowired
  private JavaMailSender emailSender;

  @Override
  @Async
  @Retryable(maxAttempts = 5)
  public void sendOrderMail(String restaurantMail, OrderDto orderDto) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(restaurantMail); 
      message.setSubject("New order created"); 
      message.setText(mapOrderDetail(orderDto));
      emailSender.send(message);
    } catch(MailException e) {
      throw new RuntimeException("Error sending email: ", e);
    }

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
    for (MealDto meal : orderDto.getMeals()) {
      sb.append(meal.getMealName());
      sb.append(": ");
      sb.append(meal.getMealDescription());
      sb.append(" ($");
      sb.append(meal.getMealPrice());
      sb.append("), ");
    }
    return sb.toString();
  }

}
