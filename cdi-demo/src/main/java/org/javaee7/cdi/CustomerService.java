package org.javaee7.cdi;

import javax.inject.Inject;
import java.util.logging.Logger;

public class CustomerService {
	@Inject
	private Logger logger ;

	public void create(Customer customer) {
		logger.info("----- Created a new customer with login name: " + customer
				.get_credentials()
				.get_name());
	}
}
