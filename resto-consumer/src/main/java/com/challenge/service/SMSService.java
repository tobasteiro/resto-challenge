package com.challenge.service;

public interface SMSService {

  /**
   * Sends sms to the client mocked.
   * 
   * @param client phoneNumber.
   */
  void sendSmsToClient(String phoneNumber);

}
