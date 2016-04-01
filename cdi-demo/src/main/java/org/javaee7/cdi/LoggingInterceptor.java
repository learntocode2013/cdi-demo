package org.javaee7.cdi;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Loggable
public class LoggingInterceptor {
	@Inject
	private Logger logger;

	@AroundInvoke
	public Object logMethod(InvocationContext ic) throws Exception {
		logger.entering(ic.getTarget().getClass().getName(),ic.getMethod().getName());
		try {
			return ic.proceed();
		} finally {
			logger.exiting(ic.getTarget().getClass().getName(),ic.getMethod().getName());
		}
	}
}
