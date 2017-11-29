package com.spring.boot;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.Lifecycle;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by mgupta on 11/28/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaApplicationTest {

	protected final static String TOPIC = "helloworld.t";

	@Autowired
	private KafkaListenerEndpointRegistry registry;

	@Autowired
	private Receiver receiver;

	@Autowired
	private Sender sender;

	// An embedded Kafka broker is automatically started by using a @ClassRule.
	// As the embedded server is started on a random port, we provide a dedicated src/test/resources/application.yml properties file for testing
	// which uses the spring.embedded.kafka.brokers system property that the @ClassRule sets to the address of the broker
	// to use local kafka server, Just comment out the @ClassRule and change the 'bootstrap-servers' property to the address of the local broker
	@ClassRule
	public static KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1, true, TOPIC);

	@Before
	public void runBeforeTestMethod() throws Exception {
		// wait until all partitions are assigned, otherwise the message might be sent before the listeners are assigned
		for (MessageListenerContainer container : registry.getListenerContainers()) {
			ContainerTestUtils.waitForAssignment(container, kafkaEmbedded.getPartitionsPerTopic());
		}
	}

	@After
	public void tearDown() {
		registry.getListenerContainers().forEach(Lifecycle::stop);
	}

	@Test
	public void testReceive() throws InterruptedException {
		sender.send(TOPIC, "Hello Spring Kafka!");
		receiver.getLatch().await(10, TimeUnit.SECONDS);
		assertEquals(0, receiver.getLatch().getCount());
	}

}

