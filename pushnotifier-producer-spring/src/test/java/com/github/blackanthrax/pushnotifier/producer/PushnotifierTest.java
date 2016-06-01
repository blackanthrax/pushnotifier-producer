package com.github.blackanthrax.pushnotifier.producer;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.github.blackanthrax.pushnotifier.producer.auth.Session;
import com.github.blackanthrax.pushnotifier.producer.config.EnablePushNotifications;
import com.github.blackanthrax.pushnotifier.producer.messaging.Device;
import com.github.blackanthrax.pushnotifier.producer.messaging.Message;
import com.github.blackanthrax.pushnotifier.producer.messaging.MessageResponse;
import com.github.blackanthrax.pushnotifier.producer.messaging.MessageType;
import com.github.blackanthrax.pushnotifier.producer.messaging.MessagingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PushnotifierTest {
    
    @Autowired
    private MessagingService svc;
    
    @Autowired
    private Session session;
    
    @Test
    public void testAuth(){
        if(session.getApiToken().equals(null) | session.getAppToken().equals(null)){
            Assert.fail();
        }
    }
    
    @Test
    public void testMessaging() {
        Message msg = new Message();
        if(svc.getDevices().getDevices().iterator().hasNext()){
            Device d = svc.getDevices().getDevices().iterator().next();
            msg.setDeviceId(d.getId());
        } else {
            Assert.fail();
        }
        msg.setType(MessageType.MESSAGE);
        msg.setText(String.format("Test %s", new Date()));
        MessageResponse resp = svc.sendMessage(msg);
    }

    @Configuration
    @EnablePushNotifications
    static class TestConfiguration {
        
        @Bean
        public PropertyPlaceholderConfigurer conf(){
            PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
            ppc.setLocation(new PathMatchingResourcePatternResolver().getResource("classpath:application.properties"));
            return ppc;
        }

        @Bean
        public RestTemplate restTemplate() {
            RestTemplate template = new RestTemplate();
            return template;
        }
    }
}
