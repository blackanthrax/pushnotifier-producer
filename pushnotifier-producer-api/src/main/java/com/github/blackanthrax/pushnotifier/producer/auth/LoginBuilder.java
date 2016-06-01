package com.github.blackanthrax.pushnotifier.producer.auth;

public class LoginBuilder {

    private String username;
    private String password;
    private String apiToken;

    public LoginBuilder username(String username) {
        this.username = username;
        return this;
    }

    public LoginBuilder password(String password) {
        this.password = password;
        return this;
    }

    public LoginBuilder apiToken(String token) {
        this.apiToken = token;
        return this;
    }

    public Login build() {
        return new Login(this.username, this.password, this.apiToken);
    }

    public class Login {

        private String username;
        private String password;
        private String apiToken;

        Login(String username, String password, String apiToken) {
            this.username = username;
            this.password = password;
            this.apiToken = apiToken;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getApiToken() {
            return apiToken;
        }

    }
}
