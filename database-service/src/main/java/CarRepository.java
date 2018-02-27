/**
 * Created by mgupta on 2/23/18.
 */

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {

	// Converted to "SELECT * FROM Car WHERE Make = 'make'" by spring
	Iterable<Car> findByMakeIgnoringCase(String make);

}
