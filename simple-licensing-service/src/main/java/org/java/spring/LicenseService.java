package org.java.spring;

import java.util.UUID;

import org.java.spring.model.License;
import org.springframework.stereotype.Service;

/**
 * Created by mgupta on 11/21/17.
 */
@Service
public class LicenseService {

	public License getLicense(String licenseId) {
		return new License().withId(licenseId).withOrganizationId(UUID.randomUUID().toString()).withProductName("Test Product").withLicenseType("PerSeat");
	}
}
