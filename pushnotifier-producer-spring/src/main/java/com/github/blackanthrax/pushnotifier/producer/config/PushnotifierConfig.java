package com.github.blackanthrax.pushnotifier.producer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.github.blackanthrax.pushnotifier.producer.RestTemplateAuthentication;
import com.github.blackanthrax.pushnotifier.producer.RestTemplateMessagingService;
import com.github.blackanthrax.pushnotifier.producer.auth.AuthenticationService;
import com.github.blackanthrax.pushnotifier.producer.auth.LoginBuilder;
import com.github.blackanthrax.pushnotifier.producer.auth.LoginBuilder.Login;
import com.github.blackanthrax.pushnotifier.producer.auth.Session;
import com.github.blackanthrax.pushnotifier.producer.messaging.MessagingService;

@Configuration
public class PushnotifierConfig {
    
    @Value("${pushnotifier.producer.apiToken}")
    private String apiToken;
    
    @Value("${pushnotifier.producer.packageName}")
    private String appPackageName;
    
    @Value("${pushnotifier.producer.password}")
    private String password;
    
    @Value("${pushnotifier.producer.username}")
    private String username;
    
    @Bean
    public Login login(){
        return new LoginBuilder()
            .apiToken(apiToken)
            .password(password)
            .username(username)
            .build();
    }
    
    @Bean
    public Session pushnotifierAuthentication(Login login, RestTemplate rest){
        AuthenticationService auth = new RestTemplateAuthentication(rest);
        return auth.login(login);
    }
    
    @Bean
    public MessagingService pushnotifierMessagingService(RestTemplate restTemplate, Session session){
        return new RestTemplateMessagingService(restTemplate, session, appPackageName);
    }
    
}
