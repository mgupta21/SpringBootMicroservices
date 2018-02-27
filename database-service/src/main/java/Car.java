/**
 * Created by mgupta on 2/23/18.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Entity represents the data we want to store
@Entity
public class Car {

	@Id
	@GeneratedValue
	private long id;

	private String make;

	private String model;

	private int year;

	// Required by JPA
	Car() {
	}

	public Car(String make, String model, int year) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return make + " " + model + " " + year;
	}

}