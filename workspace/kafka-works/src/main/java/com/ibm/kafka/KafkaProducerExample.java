package com.ibm.kafka;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducerExample {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaProducerExample.class.getName());
	
	public static void main(String[] args) {
	
		String host ="localhost";
		String port = "9092"; 
		
		Properties prop = new Properties(); 
//		prop.put("bootstrap.servers", host+":"+port); 
		
		prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host+":"+port); 
		prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop); 
		
		ProducerRecord<String, String> producerRecord = 
				new ProducerRecord<String, String>("ibm-java-topic", "Hello World"); 
		
		producer.send(producerRecord, new Callback() {
			
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if(exception == null) {
					// the record is sent to kafka and good
					
					log.info("------------------------------------------------"); 
					log.info("Record sent Successfully"); 
					log.info("Topic " + metadata.topic() +
							"\nPartition " + metadata.partition() +
							"\nTime Stamp " + new Date(metadata.timestamp() )+
							"\nOffset " + metadata.offset());
				}else {
					log.error("Sorry! Error While Saving : "+ exception.getMessage());
				}
			}
		});
		
		producer.flush();
		
		producer.close(); 
	}
}






