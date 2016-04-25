package org.javaee7.cdi;

import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemServerConnectionTest {
	private ItemServerConnection _serverConnection ;
	private static Validator _validator ;

	@BeforeClass
	public static void beforeAllTests() {
		_validator = Validation
				.buildDefaultValidatorFactory()
				.getValidator() ;
	}

	@Before
	public void beforeTest() {
		//-- Initialize without any state. State will be set from test
		_serverConnection = new ItemServerConnection(null,null);
	}

	@After
	public void afterTest() {
		_serverConnection = null ;
	}

	@AfterClass
	public static void afterAllTests() {
		_validator = null ;
	}

	@Test
	public void serverURLCannotBeInsecure() {
		_serverConnection.setItemUrl("http://www.cisco.com/v1/item");
		final Set<ConstraintViolation<ItemServerConnection>> constraintViolations = _validator
				.validate(_serverConnection);
		assertThat(constraintViolations.isEmpty(),is(equalTo(false))) ;
		final String violationMsg = constraintViolations.stream()
				.map(violation -> violation.getMessage())
				.findFirst()
				.get();

		assertThat(violationMsg,containsString("malformed URL"));
	}

	@Test
	public void serverCannotSpecifyNonStandardPort() {
		_serverConnection.setFtpServer("ftp://www.cisco.com:23");
		final Set<ConstraintViolation<ItemServerConnection>> constraintViolations = _validator
				.validate(_serverConnection);
		assertThat(constraintViolations.isEmpty(),is(equalTo(false))) ;
		final String violationMsg = constraintViolations.stream()
				.map(violation -> violation.getMessage())
				.findFirst()
				.orElse("No-value");

		assertThat(violationMsg,containsString("malformed URL"));
	}
}
