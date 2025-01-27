package com.idld.resultatservice.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
