package com.ibm.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.CooperativeStickyAssignor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.RangeAssignor;
import org.apache.kafka.clients.consumer.RoundRobinAssignor;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaConsumerShutdownHookExample {
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumerShutdownHookExample.class.getName());

	public static void main(String[] args) {
		
		String bootstrapServer="localhost:9092"; 
		
		Properties props = new Properties(); 
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer); 
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); 
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); 
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // none/latest/earliest 
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-java-group"); 
		props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, CooperativeStickyAssignor.class.getName()); 
//		props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RoundRobinAssignor.class.getName()); 
//		props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RangeAssignor.class.getName()); 
		
		 
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props); 
		
		
		
		System.out.println(Thread.currentThread().getName());
		Thread mainThread = Thread.currentThread(); 
		
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				log.info("Detected shutdown, lets exit by calling wakeup()");
				consumer.wakeup(); 
				
				try {
					mainThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		// this is 1st thread - main 
		try {
		consumer.subscribe(Arrays.asList("ibm-second-topic"));
		int i=0; 
		while(i++<50) {
			log.info("------ Polling ---------");
			// ctrl 2 + l to create a variable and assign 
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(2000));
			
			for(ConsumerRecord<String, String> record : records) {
				log.info(
					"Key :" + record.key() +
					"\n Value " + record.value() +
					"\nOffset " + record.offset()  + 
					"\nPartition " + record.partition());
			}
		} 
		}catch(WakeupException we) {
			// when the consumer throws an exception we shall get this 
			log.error("wake up exception called " + we.getMessage() );
		}catch(Exception e) {
			log.error("Something went wrong " + e.getMessage());
		}finally {
			consumer.close(); 
			log.info("Exiting the program");
			
		}
	}
}















