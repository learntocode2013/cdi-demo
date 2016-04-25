package org.javaee7.cdi;

import javax.inject.Inject;
import java.util.logging.Logger;

public class CustomerService {
	@Inject
	private Logger logger ;

	public boolean create(Customer customer) {
		return false;
	}
}
