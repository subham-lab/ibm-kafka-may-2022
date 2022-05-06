package com.ibm.kafka.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.kafka.spring.service.KafkaService;

@RestController
public class KafkaController {
	@Autowired
	private KafkaService kafkaService;
	
	// http://localhost:8080/producer/hello
	@GetMapping(value = "/producer/{message}")
	public String ackMessage( @PathVariable("message") String message) {
		kafkaService.sendMessage(message);
		return "Delivered :" + message;
	}
	
}
