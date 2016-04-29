package org.javaee7.cdi.constraints;

import org.javaee7.cdi.validators.URLValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = {URLValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD,TYPE,METHOD,PARAMETER,CONSTRUCTOR})
public @interface URL {
	String message() default "malformed URL" ;
	Class<?>[] groups() default {} ;
	Class<? extends Payload>[] payload() default {} ;

	//--- Expected values ; will be validated with actual
	String protocol() default "" ;
	String host() default "" ;
	int port() default -1 ;
}
