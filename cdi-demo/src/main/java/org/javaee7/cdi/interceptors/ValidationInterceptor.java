package org.javaee7.cdi.interceptors;

import org.javaee7.cdi.annotations.Sanitize;

import javax.annotation.Priority;
import javax.inject.Inject;
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
				return ic.proceed();
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
