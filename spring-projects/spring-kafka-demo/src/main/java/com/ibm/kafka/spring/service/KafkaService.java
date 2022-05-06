package com.ibm.kafka.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
	// when you say auto wired spring will do dependency injection 
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate; 
	private String kafkaTopic = "spring-boot-topic"; 
	
	// methods 
	public void sendMessage(String message) {
		kafkaTemplate.send(kafkaTopic, message);
	}
}
