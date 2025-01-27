package com.idld.communicationservice.Producer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumer {

    private static final String TOPIC = "grades-notifications";
    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = TOPIC, groupId = "grades-notifications-group")
    public void consume(String message) {
        messages.add(message);
        System.out.println("Message reçu: " + message);
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }
}
