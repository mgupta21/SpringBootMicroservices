import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mgupta on 2/22/18.
 */
@RestController
public class Controller {

	@RequestMapping("/")
	public String hello() {
		return "Hello World!";
	}
}
