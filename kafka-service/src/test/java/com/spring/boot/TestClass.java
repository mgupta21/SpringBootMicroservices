package com.spring.boot;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by mgupta on 12/7/17.
 */
public class TestClass {

	@Value("${test.test2}")
	private String test;


	public String getTest() {
		return test;
	}


}
