package com.github.blackanthrax.pushnotifier.producer.auth;

import com.github.blackanthrax.pushnotifier.producer.auth.LoginBuilder.Login;

public interface AuthenticationService {

    static final String LOGIN_URI = "http://a.pushnotifier.de/1/login?username=%s&password=%s&apiToken=%s";
    Session login(Login login);
}
