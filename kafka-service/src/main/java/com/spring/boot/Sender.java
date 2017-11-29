package com.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Created by mgupta on 11/28/17.
 */
public class Sender {
	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	// kafkaTemplate wraps a Producer and provides convenience methods to send data to Kafka topics
	// KafkaTemplate is autowired as its creation will be done separately in SenderConfig
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;


	public void send(String topic, String payload) {
		LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
		// The template provides asynchronous send methods which return a ListenableFuture
		kafkaTemplate.send(topic, payload);
	}
}
