package org.javaee7.cdi.constraints;

import org.javaee7.cdi.validators.URLValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Size(min = 16, message = "URL must be atleast {min} characters long")
@Constraint(validatedBy = {URLValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD,TYPE,METHOD,PARAMETER,CONSTRUCTOR})
public @interface URL {
	String message() default "{org.javaee7.cdi.constraints.URL.message}" ;
	Class<?>[] groups() default {} ;
	Class<? extends Payload>[] payload() default {} ;

	//--- Expected values ; will be validated with actual
	String protocol() default "" ;
	String host() default "" ;
	int port() default -1 ;
}
