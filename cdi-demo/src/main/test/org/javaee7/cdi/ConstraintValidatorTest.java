package org.javaee7.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConstraintValidatorTest {
	private static WeldContainer _cdiContainer     ;
	private static Validator     _validator        ;
	private ItemServerConnection _serverConnection ;
	private Order                _customerOrder    ;
	private CardValidator       _cardValidator     ;
	private static InventoryService    _inventory  ;
	private static Properties   _constraintMessages;

	@BeforeClass
	public static void beforeAllTests() {
		_cdiContainer = new Weld().initialize();
		_validator = Validation
				.buildDefaultValidatorFactory()
				.getValidator() ;
		_constraintMessages = new Properties();
		_constraintMessages
				.put("org.javaee7.cdi.constraints.Email.message",
						"Specified Email address is not valid");
		_constraintMessages
				.put("org.javaee7.cdi.constraints.URL.message",
						"Specified URL is malformed");
		_constraintMessages
				.put("org.javaee7.cdi.constraints.ChronologicalDates.message",
						"Order creation date " + System.lineSeparator() +
						"  must occur before order payment date which must " +
								"occur before order delivery date");
		_inventory = _cdiContainer
				.select(InventoryService.class)
				.get();
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
		//_validator = null ;
		if( _cdiContainer.isRunning() ) {
			_cdiContainer.shutdown();
		}
	}

	@Test
	public void serverURLMustBeOfMinimumLength() {
		_serverConnection.setItemUrl("http://a.c");
		final Set<ConstraintViolation<ItemServerConnection>> constraintViolations =
				_validator.validate(_serverConnection);
		assertThat(constraintViolations.isEmpty(),is(equalTo(false))) ;
		final String violationMsg = constraintViolations.stream()
				.map(violation -> violation.getMessage())
				.findFirst()
				.get();
		assertThat(violationMsg,anyOf(containsString(_constraintMessages
				.getProperty("org.javaee7.cdi.constraints.URL.message")),
				containsString("URL must be atleast 16 characters long")));
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

		assertThat(violationMsg,containsString(_constraintMessages
				.getProperty("org.javaee7.cdi.constraints.URL.message")));
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

		assertThat(violationMsg,anyOf(containsString(_constraintMessages
				.getProperty("org.javaee7.cdi.constraints.URL.message")),
				containsString("URI port must always be 21")
		));
	}

	@Test
	public void orderDatesConformingToConstraintsIsNotFlagged() {
		_customerOrder = new Order(
				LocalDate.now().minusDays(2),
				LocalDate.now().plusDays(3),
				"AMZ-2016-04-25-A01",
				LocalDate.now(),2456.83
				);
		final Set<ConstraintViolation<Order>> constraintViolations = _validator
				.validate(_customerOrder, Default.class);
		final List<String> messages = constraintViolations.stream()
				.map(violation -> violation.getMessage())
				.collect(Collectors.toList());
		assertThat(messages.isEmpty(),is(equalTo(true)));
	}

	@Test
	public void orderDatesViolatingConstraintsAreFlagged() {
		_customerOrder = new Order(
				LocalDate.now(),
				LocalDate.now(),
				"AMZ-2016-04-25-A01",
				LocalDate.now(),2456.83
		);
		final Set<ConstraintViolation<Order>> constraintViolations = _validator.validate(_customerOrder);
		final List<String> messages = constraintViolations.stream()
				.map(violation -> violation.getMessage())
				.peek(message -> System.out.println(message))
				.collect(Collectors.toList());
		assertThat(messages.isEmpty(),is(equalTo(false)));
		assertThat(messages.size(),is(equalTo(3)));
		assertThat(messages,hasItem(containsString("must occur before")));
	}

	@Test
	public void validCreditCardPassesCheck() {
		CreditCard card = new CreditCard(322,1641982134218731L,"visa",LocalDate
				.now().plusYears(10));
		_cardValidator = _cdiContainer.select(CardValidator.class).get();
		_cardValidator.validate(card);
		//assertThat(_inventory.fetchAllCards(),hasItem(card));
	}

	@Test
	public void creditCardCheckFailsIfCardInvalid() throws NoSuchMethodException {
		CreditCard card = null ;
		//--- Validate first and then allow method invocation
		_cardValidator = _cdiContainer.select(CardValidator.class).get();
		_cardValidator.validate(card);
	}

	@Test
	public void cannotCreateAccountWithInvalidDetails() {
		Account account = _cdiContainer.select(Account.class).get();
		account.set_contactNumber("990");
	}
}
