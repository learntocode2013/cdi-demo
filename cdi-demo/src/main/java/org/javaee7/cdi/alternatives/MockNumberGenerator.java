package org.javaee7.cdi.alternatives;

import org.javaee7.cdi.Digit;
import org.javaee7.cdi.NumberGenerator;
import org.javaee7.cdi.annotations.NumberOfDigits;

import javax.enterprise.inject.Alternative;

@Alternative @NumberOfDigits(value = Digit.THIRTEEN_DIGITS, odd = true)
public class MockNumberGenerator implements NumberGenerator{
	@Override
	public String generate() {
		return "MOCK";
	}
}
