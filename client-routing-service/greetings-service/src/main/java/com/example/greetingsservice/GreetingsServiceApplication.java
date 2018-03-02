package com.example.greetingsservice;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GreetingsServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(GreetingsServiceApplication.class, args);
	}
}

@Slf4j
@RestController
class GreetingsRestController {
	@RequestMapping(method = RequestMethod.GET, value = "/greetings/{name}")
	public Map<String, String> greeting(@PathVariable String name, @RequestHeader("x-forwarded-host") Optional<String> host, @RequestHeader("x-forwarded-port") Optional<Integer> port) {
		host.ifPresent(h -> log.info("host = " + h));
		port.ifPresent(p -> log.info("port = " + p));
		return Collections.singletonMap("greetings", "Hello, " + name + "!");
	}
}