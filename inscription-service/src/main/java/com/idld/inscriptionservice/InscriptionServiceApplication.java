package com.idld.inscriptionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableFeignClients
public class InscriptionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InscriptionServiceApplication.class, args);
    }

}
