package org.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by mgupta on 11/21/17.
 */

// Spring Bootstrap class that is used by Spring Boot to start up and initialize the application
// Marks the class as a configuration class, then begins auto-scanning all the classes on the Java class path for other Spring Beans
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
