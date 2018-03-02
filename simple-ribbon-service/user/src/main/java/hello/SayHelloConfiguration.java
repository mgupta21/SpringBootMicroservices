package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;

/**
 * Created by mgupta on 3/1/18.
 */
// The @SpringBootApplication annotation on the UserApplication class is equivalent to (among others) the @Configuration annotation
// that marks a class as a source of bean definitions. This is why we don’t need to annotate the SayHelloConfiguration class with @Configuration:
// since it’s in the same package as UserApplication
public class SayHelloConfiguration {

	@Autowired
	IClientConfig ribbonClientConfig;

	// Our IPing is a PingUrl, which will ping a URL to check the status of each server.
	// Say Hello has a method mapped to the / path; that means that Ribbon will get an HTTP 200 response when it pings a running Say Hello server.
	@Bean
	public IPing ribbonPing(IClientConfig config) {
		return new PingUrl();
	}

	// AvailabilityFilteringRule, will use Ribbon’s built-in circuit breaker functionality to filter out any servers in an “open-circuit” state:
	// if a ping fails to connect to a given server, or if it gets a read failure for the server, Ribbon will consider that server “dead” until it begins to respond normally.
	@Bean
	public IRule ribbonRule(IClientConfig config) {
		return new AvailabilityFilteringRule();
	}

}
