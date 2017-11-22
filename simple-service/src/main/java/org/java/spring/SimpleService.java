package org.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mgupta on 11/21/17.
 */
@SpringBootApplication
@RestController
@RequestMapping(value = "hello")
public class SimpleService {

	public static void main(String[] args) {
		SpringApplication.run(SimpleService.class, args);
	}

	//http://localhost:8080/hello/mayank/gupta
	@RequestMapping(value = "/{firstName}/{lastName}", method = RequestMethod.GET)
	public String hello(@PathVariable("firstName") String firstName,
						@PathVariable("lastName") String lastName) {

		return String.format("{\"message\":\"Hello %s %s\"}", firstName, lastName);
	}
}
