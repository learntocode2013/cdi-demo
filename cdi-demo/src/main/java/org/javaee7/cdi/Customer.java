package org.javaee7.cdi;

import org.javaee7.cdi.producers.Account;

public class Customer {
	private final Login _credentials ;
	private final Account _details ;

	public Customer(Login _credentials, Account _details) {
		this._credentials = _credentials;
		this._details = _details;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"_details=" + _details +
				'}';
	}

	public Login get_credentials() {
		return _credentials;
	}

	public Account get_details() {
		return _details;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Customer customer = (Customer) o;

		if (!_credentials.equals(customer._credentials)) return false;
		return _details.equals(customer._details);

	}

	@Override
	public int hashCode() {
		int result = _credentials.hashCode();
		result = 31 * result + _details.hashCode();
		return result;
	}
}
