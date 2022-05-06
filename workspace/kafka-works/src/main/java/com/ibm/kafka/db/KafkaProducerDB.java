package com.ibm.kafka.db;

import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.kafka.KafkaProducerExample;
import com.ibm.kafka.beans.Employee;
import com.ibm.kafka.dao.EmployeeDAO;
import com.ibm.kafka.interfaces.IEmployeeDAO;
import com.ibm.kafka.util.KafkaJsonSerializer;
// https://medium.com/@asce4s/send-and-receive-json-objects-with-kafka-java-client-41bfbb4de108

public class KafkaProducerDB {

	private static final Logger log = LoggerFactory.getLogger(KafkaProducerExample.class.getName());

	public static void main(String[] args) {

		String host = "localhost";
		String port = "9092";

		Properties prop = new Properties();

		prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host + ":" + port);
		prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		IEmployeeDAO dao = new EmployeeDAO();

		Scanner sc = new Scanner(System.in); 
		System.out.println("Enter Number of Employee");
		int noOfEmps = sc.nextInt(); 
		
		
		
		// assuming you are passing the employee id
		for (int i = 1; i <=noOfEmps; i++) {
			Employee emp = dao.getemployee(i);

			ProducerRecord<String, Employee> producerRecord = new ProducerRecord<String, Employee>("first-topic",
					emp);

			Producer<String, Employee> kafkaProducer = new KafkaProducer<>(prop, new StringSerializer(),
					new KafkaJsonSerializer());

			kafkaProducer.send(producerRecord, (metadata, exception) -> {
				if (exception == null) {
					// the record is sent to kafka and good

					log.info("------------------------------------------------");
					log.info("Record sent Successfully");
					log.info("Topic " + metadata.topic() + "\nPartition " + metadata.partition() + "\nTime Stamp "
							+ new Date(metadata.timestamp()) + "\nOffset " + metadata.offset());
				} else {
					log.error("Sorry! Error While Saving : " + exception.getMessage());
				}
			});

			kafkaProducer.flush();
			kafkaProducer.close();
		}
	}
}
