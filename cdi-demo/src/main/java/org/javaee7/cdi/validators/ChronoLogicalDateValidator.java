package org.javaee7.cdi.validators;

import org.javaee7.cdi.Order;
import org.javaee7.cdi.constraints.ChronologicalDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChronoLogicalDateValidator implements ConstraintValidator<ChronologicalDates,Order> {

	@Override
	public void initialize(ChronologicalDates chronologicalDates) {
	}

	@Override
	public boolean isValid(Order order, ConstraintValidatorContext constraintValidatorContext) {
		return order.getCreationDate().isBefore(order.getPaymentDate())
				&& order.getPaymentDate().isBefore(order.getDeliveryDate()) ;
	}
}
