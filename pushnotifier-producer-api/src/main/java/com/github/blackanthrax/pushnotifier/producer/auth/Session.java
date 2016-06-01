package com.github.blackanthrax.pushnotifier.producer.auth;

public class Session extends LoginResponse {

    private String apiToken;
    private String status;
    private String username;
    private String appToken;
    private String code;

    public Session(LoginResponse loginResponse) {
        this.appToken = loginResponse.getAppToken();
        this.status = loginResponse.getStatus();
        this.code = loginResponse.getCode();
        this.username = loginResponse.getUsername();
    }   
    
    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiToken() {
        return apiToken;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public String getAppToken() {
        return appToken;
    }

    public String getCode() {
        return code;
    }

}
