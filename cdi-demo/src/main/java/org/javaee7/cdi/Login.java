package org.javaee7.cdi;

public class Login {
	private final String _name ;
	private final String _password ;

	public Login(String _name, String _password) {
		this._name = _name;
		this._password = _password;
	}

	@Override
	public String toString() {
		return "Login{" +
				"_name='" + _name + '\'' +
				", _password='" + _password + '\'' +
				'}';
	}

	public String get_name() {
		return _name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Login login = (Login) o;

		if (!_name.equals(login._name)) return false;
		return _password.equals(login._password);

	}

	@Override
	public int hashCode() {
		int result = _name.hashCode();
		result = 31 * result + _password.hashCode();
		return result;
	}
}
