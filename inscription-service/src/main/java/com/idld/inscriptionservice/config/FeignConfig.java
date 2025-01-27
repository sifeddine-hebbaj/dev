package com.idld.inscriptionservice.config;

import com.idld.inscriptionservice.security.FeignClientInterceptor;
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

