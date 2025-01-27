package com.idld.communicationservice.controller;

import com.idld.communicationservice.Dto.ResultDto;
import com.idld.communicationservice.Producer.KafkaConsumer;
import com.idld.communicationservice.Producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
@RestController
@RequestMapping("/api/kafka")
public class KafkaController {


    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Autowired
    private KafkaProducer kafkaProducer;

    // Endpoint pour envoyer des messages à Kafka
    @PostMapping("/send")
    public ResponseEntity<String> sendMessageToKafka(@RequestBody String message) {
        try {
            kafkaProducer.sendMessage(message);
            return ResponseEntity.ok("Message envoyé avec succès à Kafka.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'envoi du message à Kafka.");
        }
    }

    // Endpoint pour récupérer les messages consommés depuis Kafka
    @GetMapping("/messages")
    public List<String> getMessagesFromKafka() {
        return kafkaConsumer.getMessages();
    }


}

