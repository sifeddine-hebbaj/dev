package com.idld.resultatservice.security;

import com.idld.resultatservice.service.TokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
public class FeignClientInterceptor implements RequestInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = tokenService.getToken();

        if (token != null) {
            requestTemplate.header("Authorization", "Bearer " + token);
        }
    }
}
