package com.challenge.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.challenge.exception.KafkaException;

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
   * @throws KafkaException. 
   */
  @Async
  public void send(T payload, String topic) throws KafkaException {
    LOG.info("sending message='{}'", payload.toString());
    try {
    	kafkaTemplate.send(topic, payload);
    } catch(Exception e) {
    	throw new KafkaException("Error sending message to topic", e);
    }
  }

}
