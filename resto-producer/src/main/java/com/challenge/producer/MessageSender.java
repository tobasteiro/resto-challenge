package com.challenge.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MessageSender<T> {

  private static final Logger LOG = LoggerFactory.getLogger(MessageSender.class);

  @Autowired
  private KafkaTemplate<String, T> kafkaTemplate;

  /**
   * Sends a message to kafka.
   * 
   * @param payload information.
   * @param topic name.
   */
  @Async
  public void send(T payload, String topic) {
    LOG.info("sending car='{}'", payload.toString());
    kafkaTemplate.send(topic, payload);
  }

}
