package org.javaee7.cdi;

import org.javaee7.cdi.annotations.Added;
import org.javaee7.cdi.annotations.Sanitize;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.logging.Logger;


public class CardValidator {
	@Inject
	private Logger _logger ;
	@Inject
	private ValidationAlgorithm _algorithm ;

	@Inject
	@Added
	private Event<CreditCard> cardAddedEvent ;


	//--- Since a global validator checks for invalid values, we can do away with
	//--- null checks
//	@AssertTrue
	@Sanitize
	public void validate(@NotNull(message = "Cannot validate a null card")
			                            CreditCard card) {
		//--- Allow to save the credit card only if it is valid
		if( _algorithm.validate(card.getNumber(), card.getCvvNumber()) ) {
			cardAddedEvent.fire(card);
		}
	}

	@AssertTrue
	public Boolean validate(@NotNull String number, String cvvNum, String type,
	                        @Future LocalDate expiry) {
		return _algorithm.validate(
				Long.parseLong(number),
				Integer.parseInt(cvvNum),
				expiry,
				type
				);
	}
}
