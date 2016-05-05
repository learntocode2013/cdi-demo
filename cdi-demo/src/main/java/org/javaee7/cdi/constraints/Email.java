package org.javaee7.cdi.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


@NotNull(message = "Email id must be specified")
@Size(min = 7, message = "Email id must be atleast {min} characters long")
@Pattern(regexp = "([aA-zZ]+)@([aA-zZ]+)(\\.)(([aA-zZ]{3,3}))",
		message = "Provided string is not an email id")
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD,TYPE,METHOD,PARAMETER,CONSTRUCTOR})
public @interface Email {
	String message() default "{org.javaee7.cdi.constraints.Email.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
