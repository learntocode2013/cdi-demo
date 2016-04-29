package org.javaee7.cdi;

import java.time.LocalDate;

public class ValidationAlgorithm {

	public boolean validate(long cardNumber, int cvvNumber) {
		return
				String.valueOf(cardNumber).startsWith("1641") &&
				String.valueOf(cvvNumber).startsWith("3") ;
	}

	public boolean validate(long cardNumber, int cvvNumber, LocalDate expiry, String type) {
		return
				validate(cardNumber,cvvNumber)
						&&
				expiry.isBefore(expiry.plusYears(10))
						&&
				type.matches("visa") ;
	}
}
