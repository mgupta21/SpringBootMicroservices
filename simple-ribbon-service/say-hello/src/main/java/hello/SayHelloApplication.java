package hello;

/**
 * Created by mgupta on 3/1/18.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

// Our “server” service is called Say Hello. It will return a random greeting from an endpoint accessible at /greeting
@RestController
@SpringBootApplication
public class SayHelloApplication {

	private static Logger log = LoggerFactory.getLogger(SayHelloApplication.class);

	@RequestMapping(value = "/greeting")
	public String greet() {
		log.info("Access /greeting");

		List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
		Random rand = new Random();

		int randomNum = rand.nextInt(greetings.size());
		return greetings.get(randomNum);
	}

	@RequestMapping(value = "/")
	public String home() {
		log.info("Access /");
		return "Reachable!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SayHelloApplication.class, args);
	}
}
