package com.challenge.receiver;

import com.challenge.dto.OrderDto;
import com.challenge.service.EmailService;
import com.challenge.service.SMSService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
  private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);
  
  @Autowired
  private EmailService mailService;

  @Autowired
  private SMSService smsService;

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.topic.order}")
  public void receive(OrderDto orderDto) {
    LOG.info("received OrderDto='{}'", orderDto.toString());
    latch.countDown();
    
    mailService.sendOrderMail(orderDto.getRestaurantMail(), orderDto);
    smsService.sendSmsToClient(orderDto.getPhoneNumber());
  }

}
