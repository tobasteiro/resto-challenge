package com.challenge.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SMSServiceImpl implements SMSService {

  private static final Logger LOG = LoggerFactory.getLogger(SMSServiceImpl.class);

  @Override
  @Async
  @Retryable
  public void sendSmsToClient(String phoneNumber) {
    LOG.info("Sending sms to client's phone: " + phoneNumber);
  }

}
