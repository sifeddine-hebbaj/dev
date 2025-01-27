package com.idld.resultatservice.config;


import com.idld.resultatservice.security.FeignClientInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public FeignClientInterceptor feignClientInterceptor()
    {

        return new FeignClientInterceptor();
    }
}

