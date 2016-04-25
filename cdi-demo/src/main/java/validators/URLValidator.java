package validators;

import org.javaee7.cdi.constraints.URL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;

public class URLValidator implements ConstraintValidator<URL,String>{
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
		if( null != host  && 0 != host.trim().length() && !host.equals(url.getHost())) return false ;
		if( port != url.getPort() ) return false ;

		return true;
	}
}
