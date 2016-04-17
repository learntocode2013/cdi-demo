package org.javaee7.cdi;

public class Account {
	private String _firstName ;
	private String _lastName ;
	private String _contactNumber ;
	private final String _email ;

	public Account(String _email) {
		this._email = _email;
	}

	public String get_contactNumber() {
		return _contactNumber;
	}

	public void set_contactNumber(String _contactNumber) {
		this._contactNumber = _contactNumber;
	}

	public String get_firstName() {
		return _firstName;
	}

	public void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}

	public String get_lastName() {
		return _lastName;
	}

	public void set_lastName(String _lastName) {
		this._lastName = _lastName;
	}

	@Override
	public String toString() {
		return "Account{" +
				"_contactNumber='" + _contactNumber + '\'' +
				", _firstName='" + _firstName + '\'' +
				", _lastName='" + _lastName + '\'' +
				", _email='" + _email + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Account account = (Account) o;

		if (_firstName != null ? !_firstName.equals(account._firstName) : account._firstName != null) return false;
		if (_lastName != null ? !_lastName.equals(account._lastName) : account._lastName != null) return false;
		if (!_contactNumber.equals(account._contactNumber)) return false;
		return _email.equals(account._email);

	}

	@Override
	public int hashCode() {
		int result = _firstName != null ? _firstName.hashCode() : 0;
		result = 31 * result + (_lastName != null ? _lastName.hashCode() : 0);
		result = 31 * result + _contactNumber.hashCode();
		result = 31 * result + _email.hashCode();
		return result;
	}
}
