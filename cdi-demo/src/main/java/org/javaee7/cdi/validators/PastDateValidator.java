package org.javaee7.cdi.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class PastDateValidator implements ConstraintValidator<Past,LocalDate>{
	@Override
	public void initialize(Past past) {

	}

	@Override
	public boolean isValid(LocalDate srcDate, ConstraintValidatorContext constraintValidatorContext) {
		return srcDate.isBefore(LocalDate.now());
	}
}
