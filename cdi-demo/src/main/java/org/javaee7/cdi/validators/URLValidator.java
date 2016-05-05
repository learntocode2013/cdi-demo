package org.javaee7.cdi.validators;

import org.javaee7.cdi.constraints.URL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;

//TODO: Inject logger instances from CDI container
public class URLValidator implements ConstraintValidator<URL,String>{
	//------ Expected values
	private String proto    ;
	private String host     ;
	private int    port     ;

	@Override
	public void initialize(URL url) {
		proto = url.protocol() ;
		host  = url.host() ;
		port  = url.port() ;
	}

	@Override
	public boolean isValid(String webTarget, ConstraintValidatorContext constraintValidatorContext) {
		if( null == webTarget || 0 == webTarget.length() ) return true ;

		java.net.URL url ;
		try {
			url = new java.net.URL(webTarget) ;
		} catch (MalformedURLException cause) {
			cause.printStackTrace();
			return false ;
		}

		if( null != proto && !proto.equals(url.getProtocol())) return false ;

		//--- Customize violation message for host
		if( null != host  && 0 != host.trim().length() && !host.equals(url.getHost())) {
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext
					.buildConstraintViolationWithTemplate("Target host must always be " + host)
					.addConstraintViolation();
			return false ;
		}

		//--- Customize violation message for port
		if( port != url.getPort() ) {
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext
					.buildConstraintViolationWithTemplate("URI port must always be " + port)
					.addConstraintViolation();
			return false ;
		}

		return true;
	}
}
