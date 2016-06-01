package com.github.blackanthrax.pushnotifier.producer;

import org.springframework.web.client.RestTemplate;

import com.github.blackanthrax.pushnotifier.producer.auth.AuthenticationService;
import com.github.blackanthrax.pushnotifier.producer.auth.LoginBuilder.Login;
import com.github.blackanthrax.pushnotifier.producer.auth.LoginResponse;
import com.github.blackanthrax.pushnotifier.producer.auth.Session;

public class RestTemplateAuthentication implements AuthenticationService{

    private RestTemplate rest;
    
    public RestTemplateAuthentication(RestTemplate rest) {
        this.rest = rest;
    }

    public Session login(Login login) {
        String uri = String.format(LOGIN_URI, login.getUsername(), login.getPassword(),
                login.getApiToken());
        Session session = new Session(rest.getForEntity(uri, LoginResponse.class).getBody());
        session.setApiToken(login.getApiToken());
        return session;
    }

}
