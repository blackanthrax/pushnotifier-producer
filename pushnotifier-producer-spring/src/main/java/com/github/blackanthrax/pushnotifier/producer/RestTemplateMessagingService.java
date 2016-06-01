package com.github.blackanthrax.pushnotifier.producer;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.blackanthrax.pushnotifier.producer.auth.Session;
import com.github.blackanthrax.pushnotifier.producer.messaging.DeviceList;
import com.github.blackanthrax.pushnotifier.producer.messaging.Message;
import com.github.blackanthrax.pushnotifier.producer.messaging.MessageResponse;
import com.github.blackanthrax.pushnotifier.producer.messaging.MessagingService;
import com.google.common.collect.Lists;

@Component
public class RestTemplateMessagingService implements MessagingService {

    private RestTemplate rest;
    private String packageName;
    private Session session;

    public RestTemplateMessagingService(RestTemplate restTemplate, Session session, String appPackageName) {
        this.rest = restTemplate;
        this.packageName = appPackageName;
        this.session = session;
    }

    public MessageResponse sendMessage(Message msg) {
        return sendMessages(Lists.newArrayList(msg)).get(0);
    }

    public DeviceList getDevices() {
        return rest.getForEntity(
                String.format(DEVICES_URI, session.getApiToken(), session.getAppToken()),
                DeviceList.class).getBody();
    }

    public List<MessageResponse> sendMessages(List<Message> messages) {
        List<MessageResponse> responses = Lists.newArrayList();
        for (Message msg : messages) {
            String url = String.format(MESSAGE_URI, session.getApiToken(), session.getAppToken(),
                    packageName, msg.getDeviceId(), msg.getType(), msg.getText());
            MessageResponse response = rest.getForEntity(url, MessageResponse.class).getBody();
            responses.add(response);
        }
        return responses;
    }
}
