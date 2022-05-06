package com.ibm.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.RangeAssignor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaConsumerExample {
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumerExample.class.getName());

	public static void main(String[] args) {
		
		String bootstrapServer="localhost:9092"; 
		
		Properties props = new Properties(); 
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer); 
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); 
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); 
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // none/latest/earliest 
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-java-group"); 
		
		 
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props); 
		
		consumer.subscribe(Arrays.asList("ibm-second-topic"));
		
		
		while(true) {
			log.info("------ Polling ---------");
			// ctrl 2 + l to create a variable and assign 
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
			
			for(ConsumerRecord<String, String> record : records) {
				log.info(
					"Key :" + record.key() +
					"\n Value " + record.value() +
					"\nOffset " + record.offset()  + 
					"\nPartition " + record.partition());
			}
		} 
	}
}















