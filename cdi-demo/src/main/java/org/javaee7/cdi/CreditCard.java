package org.javaee7.cdi;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.time.LocalDate;

public class CreditCard {
	@Min(16)
	private final long number ;
	@Min(3)
	private final int cvvNumber ;
	@Future
	private final LocalDate expiry ;

	private final String type ;

	public CreditCard(int cvvNumber, long number, String type, LocalDate expiry) {
		this.cvvNumber = cvvNumber;
		this.number = number;
		this.type = type;
		this.expiry = expiry;
	}

	public int getCvvNumber() {
		return cvvNumber;
	}

	public LocalDate getExpiry() {
		return expiry;
	}

	public long getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CreditCard that = (CreditCard) o;

		if (number != that.number) return false;
		if (cvvNumber != that.cvvNumber) return false;
		if (!expiry.equals(that.expiry)) return false;
		return type.equals(that.type);

	}

	@Override
	public int hashCode() {
		int result = (int) (number ^ (number >>> 32));
		result = 31 * result + cvvNumber;
		result = 31 * result + expiry.hashCode();
		result = 31 * result + type.hashCode();
		return result;
	}
}
