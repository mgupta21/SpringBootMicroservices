package com.example.client;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Created by mgupta on 3/2/18.
 */
// Build simple client that will call downstream greeting service
// This is a special client called edge service, it is an interface to the service
// Takes advantage of service registry / calls service registry. check localhost:8761

// Zuul: Routes automatically created for us which proxy requests to the services that are registered in the eureka registry
// eg: Route request from client to service using -->> localhost:9999/greetings-service/greetings/Mayank
// Here service Id 'greetings-service' is used to call service registry using service client abstraction to get back service instances and pass that instances to ribbon
// Ribbon integrated in spring cloud and acts as client side load balancer
@EnableZuulProxy
// Prevents downstream service from hitting over and over if its not available
@EnableCircuitBreaker
// Makes it easy to make declarative rest clients
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GreetingsClientApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(GreetingsClientApplication.class, args);
	}
}

// Declare service to which this client should make requests
// Automatically works with ribbon, automatically knows about service discovery
@FeignClient("greetings-service")
interface GreetingsClient {

	@RequestMapping(method = RequestMethod.GET, value = "/greetings/{name}")
	Greeting greet(@PathVariable("name") String name);
}

@RestController
class GreetingsApiGatewayRestController {

	/*private final RestTemplate template;

	@Autowired
	public GreetingsApiGatewayRestController(RestTemplate template) {
		this.template = template;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hi/{name}")
	String greet(@PathVariable String name) {
		// Not using zuul here to automatically reroute
		ResponseEntity<Greeting> response = this.template.exchange("http://greetings-service/greetings/{name}", HttpMethod.GET, null, Greeting.class);
		return response.getBody().getGreeting();
	}*/

	private final GreetingsClient greetingsClient;

	@Autowired
	public GreetingsApiGatewayRestController(GreetingsClient client) {
		this.greetingsClient = client;
	}

	@HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(method = RequestMethod.GET, value = "/hi/{name}")
	String greet(@PathVariable String name) {
		return this.greetingsClient.greet(name).getGreeting();
	}

	// Used for exception handling, show this message if service is down/unreachable/warming up. Failure Resilience.
	public String fallback(String name) {
		return "ohai!";
	}
}

class Greeting {
	private String greeting;

	public String getGreeting() {
		return greeting;
	}
}

// Zuul is also very useful for handling cross cutting concerns that needs to applied to all proxy requests going to all proxy services
// Zuul is an ideal place for authentication, authorization, rate limiting, traffic shaping, traffic routing, filters etc.

// Disabled (to test run "watch -n1 curl -v http://localhost:9999/greetings-service/greetings/world")
@Component
class RateLimitingZuulFilter extends ZuulFilter {

	private final RateLimiter rateLimiter = RateLimiter.create(1.0 / 30.0);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 100;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		try {
			RequestContext currentContext = RequestContext.getCurrentContext();
			HttpServletResponse response = currentContext.getResponse();

			if (!this.rateLimiter.tryAcquire()) {
				response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
				response.getWriter().append(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
				currentContext.setSendZuulResponse(false);
			}
		} catch (IOException e) {
			ReflectionUtils.rethrowRuntimeException(e);
		}
		return null;
	}
}
