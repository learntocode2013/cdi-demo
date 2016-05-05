package org.javaee7.cdi.interceptors;

import org.javaee7.cdi.CreditCard;
import org.javaee7.cdi.Order;
import org.javaee7.cdi.annotations.Added;
import org.javaee7.cdi.annotations.Sanitize;

import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.logging.Logger;

import static javax.interceptor.Interceptor.Priority.LIBRARY_BEFORE;

@Sanitize
@Interceptor
@Priority(LIBRARY_BEFORE+10)
public class ValidationInterceptor {
	@Inject
	private Logger    _logger    ;
	@Inject
	private Validator _validator ;

	@Inject
	@Added
	private Event<Order> orderAdded ;

	@Inject
	@Added
	private Event<CreditCard> cardAddedEvent ;

	@AroundConstruct
	public void allowInstantiation(InvocationContext ic) {
		try {
			ic.proceed();
			final Set<ConstraintViolation<Object>> constraintViolations
					= _validator.validate(ic.getTarget());
			if( constraintViolations.isEmpty() ) {
				if( ic.getTarget() instanceof Order ) {
					orderAdded.fire((Order)ic.getTarget());
				}
			}
		} catch (Exception cause) {
			cause.printStackTrace();
		}
	}

	@AroundInvoke
	public Object allowMethodInvocationForValidParams(InvocationContext ic) {
		final Object targetClass = ic.getTarget();
		final Method targetMethod = ic.getMethod();
		final Object[] parameters = ic.getParameters();

		_logger.entering(targetClass.getClass().getName(),targetMethod.getName());

		final Set<ConstraintViolation<Object>> constraintViolations = _validator
				.forExecutables()
				.validateParameters(targetClass, targetMethod, parameters);
		if( constraintViolations.isEmpty() ) {
			try {
				ic.proceed();
				if( ic.getTarget() instanceof CreditCard ) {
					cardAddedEvent.fire((CreditCard) ic.getTarget());
				}
			} catch (Exception cause) {
				cause.printStackTrace();
			} finally {
				_logger.exiting(targetClass.getClass().getName(),targetMethod.getName());
			}
		}
		constraintViolations.stream()
				.map(violation -> violation.getMessage())
				.forEach( msg -> _logger.warning("#---- " + msg));
		return new Object() ;
	}
}
