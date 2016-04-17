package org.javaee7.cdi.producers;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorProducer {
	private final ValidatorFactory validatorFactory ;
	private final Validator validator ;

	public ValidatorProducer() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Produces
	public ValidatorFactory getValidatorFactory() {
		return validatorFactory;
	}

	@Produces
	public Validator getValidator() {
		return validator;
	}
}
