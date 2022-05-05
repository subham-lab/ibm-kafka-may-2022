package com.ibm.kafka;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducerMultipleMessageWithKeyExample {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaProducerMultipleMessageWithKeyExample.class.getName());
	
	public static void main(String[] args) {
	
		String host ="localhost";
		String port = "9092"; 
		
		Properties prop = new Properties(); 
		
		prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host+":"+port); 
		prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop); 
		
		
		for(int i=0; i<10; i++) {
		
			String topic = "ibm-second-topic"; 
			String value =  "Hello World " + i;
			String key = "id_" + Integer.toString(i);

			ProducerRecord<String, String> producerRecord = 
						new ProducerRecord<String, String>(topic, key, value);

		
		// if you want to send the data to particular partition 
//		new ProducerRecord<K, V>(port, Partition, Key, Value)
		
			// anytime if you are using the callback by default it is async 
		producer.send(producerRecord, ( metadata,  exception) ->  {
				if(exception == null) {
					// the record is sent to kafka and good
					
					log.info("------------------------------------------------"); 
					log.info("Record sent Successfully"); 
					log.info(
							"Topic " + metadata.topic() +
							"\nPartition " + metadata.partition() +
							"\nKey " + producerRecord.key() +
							"\nValue  " + producerRecord.value() +
							"\nTime Stamp " + new Date(metadata.timestamp() )+
							"\nOffset " + metadata.offset());
				}else {
					log.error("Sorry! Error While Saving : "+ exception.getMessage());
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		);
		 
		} // end of for loop 

		producer.flush();
		
		producer.close(); 
	}
}






