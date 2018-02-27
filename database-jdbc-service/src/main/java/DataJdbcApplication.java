import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by mgupta on 2/24/18.
 */
@SpringBootApplication
public class DataJdbcApplication {

	@Bean
	public CommandLineRunner exampleQuery(CarRepository repository) {
		return args -> repository.findByMakeIgnoringCase("HONDA")
				.forEach(System.err::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(DataJdbcApplication.class, args);
	}

}
