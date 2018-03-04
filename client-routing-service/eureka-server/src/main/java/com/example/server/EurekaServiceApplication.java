package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by mgupta on 3/4/18.
 */
// provides stand up registry that other applications can talk to
@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceApplication.class, args);
	}
}
