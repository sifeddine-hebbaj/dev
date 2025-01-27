package com.idld.communicationservice.service;


import com.idld.communicationservice.Producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageService {

    @Autowired
    private KafkaProducer kafkaProducer;

    public void sendToKafka(String message) {
        kafkaProducer.sendMessage(message);
    }
}

