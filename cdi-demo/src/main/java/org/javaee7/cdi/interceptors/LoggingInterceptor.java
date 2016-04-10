package org.javaee7.cdi.interceptors;

import org.javaee7.cdi.annotations.Loggable;

import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.time.LocalTime;
import java.util.logging.Logger;

@Loggable
@Interceptor
public class LoggingInterceptor {
	@Inject
	private Logger logger;

	@AroundConstruct
	private void logInit(InvocationContext ic) {
		try {
			ic.proceed();
		} catch (Exception ignore) {
			ignore.printStackTrace();
		} finally {
			logger.info("#---- Instantiated " + ic.getTarget()
					.getClass().getName());
		}
	}

	//----- Intercept method entry/exit via method interceptor
	@AroundInvoke
	public Object logMethod(InvocationContext ic) throws Exception {
		logger.entering(ic.getTarget().getClass().getName(),ic.getMethod().getName());
		try {
			return ic.proceed();
		} finally {
			logger.exiting(ic.getTarget().getClass().getName(),ic.getMethod().getName());
		}
	}

	//----- Intercept time taken for method completion via method interceptor
	@AroundInvoke
	private Object logTimeTaken(InvocationContext ic) throws Exception {
		final int startTimeInSecs = LocalTime.now().toSecondOfDay();
		try {
			return ic.proceed();
		} finally {
			final int endTimeInSecs = LocalTime.now().toSecondOfDay();
			logger.info(ic.getTarget() + "#" + ic.getMethod().getName()
					+ " method took "
					+ (endTimeInSecs-startTimeInSecs) + " secs to complete");
		}
	}
}
