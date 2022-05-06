package com.ibm.wikimedia;

import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

public class WikiMediaChangesProducerExample {

	private static final Logger logger = LoggerFactory.getLogger(WikiMediaChangesProducerExample.class.getName());

	public static void main(String[] args) throws InterruptedException {
		String bootstrapServer = "localhost:9092";
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		String topic ="wikimedia.recentchange"; 
		
		Properties properties = new Properties(); 
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties); 
		
		EventHandler eventHandler = new WikiMediaEventHandler(producer, topic);
			
		EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url)); 
		EventSource eventSource = builder.build(); 
		
		eventSource.start();
		
		TimeUnit.MINUTES.sleep(10);
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
