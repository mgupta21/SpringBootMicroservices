package org.java.spring;

import org.java.spring.model.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mgupta on 11/21/17.
 */
// Spring Controller class exposes the HTTP endpoints that can be invoked on the microservice by mapping the data from an incoming HTTP request to a Java method that will process the request
// RestController tells spring boot this is a REST-based service and will automatically serialize/deserialize service request/response to JSON
@RestController
// Exposes all HTTP endpoints in this class with a prefix of v1/org.../..
@RequestMapping(value = "v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
	@Autowired
	private LicenseService licenseService;

	@RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
	public License getLicenses(@PathVariable("organizationId") String organizationId,
							   @PathVariable("licenseId") String licenseId) {

		//return licenseService.getLicense(licenseId);
		return new License()
				.withId(licenseId)
				.withOrganizationId(organizationId)
				.withProductName("Insurance")
				.withLicenseType("Seat");
	}

	@RequestMapping(value = "{licenseId}", method = RequestMethod.PUT)
	public String updateLicenses(@PathVariable("licenseId") String licenseId) {
		return String.format("This is the put for license id %s", licenseId);
	}

	@RequestMapping(value = "{licenseId}", method = RequestMethod.POST)
	public String saveLicenses(@PathVariable("licenseId") String licenseId) {
		return String.format("This is the post for license id %s", licenseId);
	}

	@RequestMapping(value = "{licenseId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteLicenses(@PathVariable("licenseId") String licenseId) {
		return String.format("This is the Delete for license id %s", licenseId);
	}
}
