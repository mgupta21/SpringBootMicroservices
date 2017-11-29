package com.spring.boot;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Created by mgupta on 11/28/17.
 */
public class Receiver {
	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
	private CountDownLatch latch = new CountDownLatch(1);

	// @KafkaListener annotation creates a ConcurrentMessageListenerContainer message listener container behind the scenes
	// Using the topics element, we specify the topics for this listener. The name of the topic is injected from the application.yml
	@KafkaListener(topics = "${kafka.topic.helloworld}")
	public void receive(String payload) {
		LOGGER.info("Received payload='{}'", payload);
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
