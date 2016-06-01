package com.github.blackanthrax.pushnotifier.producer.messaging;

import java.util.List;

/**
 * 
 * @author mgora
 *
 * Provides constants for easy access of the pushnotifier HTTP-API.
 *
 * Consants:
 *  DEVICES_URI = http://a.pushnotifier.de/1/getDevices?apiToken=%s&appToken=%s
 *  MESSAGE_URI = http://a.pushnotifier.de/1/sendToDevice?apiToken=%s&appToken=%s&app=%s&deviceID=%s&type=%s&content=%s
 */
public interface MessagingService {

    static final String DEVICES_URI = "http://a.pushnotifier.de/1/getDevices?apiToken=%s&appToken=%s";
    static final String MESSAGE_URI = "http://a.pushnotifier.de/1/sendToDevice?apiToken=%s&appToken=%s&app=%s&deviceID=%s&type=%s&content=%s";

    MessageResponse sendMessage(Message msg);
    DeviceList getDevices();
    List<MessageResponse> sendMessages(List<Message> messages);
    
}
