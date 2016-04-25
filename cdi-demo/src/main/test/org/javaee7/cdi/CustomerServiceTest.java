package org.javaee7.cdi;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomerServiceTest {
	private CustomerService _service ;
	private static Validator _constraintValidator;

	@BeforeClass
	public static void beforeAllTests() {
		_constraintValidator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Before
	public void beforeTest() {
		_service = new CustomerService();
	}

	@After
	public void afterTest() {
		_service = null ;
	}

	@Test
	public void cannotCreateCustomerWithoutEmailId() {
		final Account account = new Account(null);
		final Set<ConstraintViolation<Account>> constraintViolations = _constraintValidator.validate(account);
		assertThat(constraintViolations.isEmpty(),is(equalTo(false)));
		final String violationMsg = constraintViolations
				.stream()
				.map(violation -> violation.getMessage())
				.findFirst()
				.get();
		assertThat(violationMsg,containsString("Email id must be specified"));
	}

	@Test
	public void cannotCreateCustomerWithInvalidEmail() {
		final Account account = new Account("disen@ciscocom");
		final Set<ConstraintViolation<Account>> constraintViolations = _constraintValidator.validate(account);
		assertThat(constraintViolations.isEmpty(),is(equalTo(false)));
		final String violationMsg = constraintViolations.stream().map(violation -> violation.getMessage())
				.filter(message -> message.contains("email"))
				.findFirst()
				.get();
		assertThat(violationMsg,containsString("Provided string is not an email id"));
	}
}
