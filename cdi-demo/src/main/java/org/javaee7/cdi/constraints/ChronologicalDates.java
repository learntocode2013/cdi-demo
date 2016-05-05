package org.javaee7.cdi.constraints;

import org.javaee7.cdi.validators.ChronoLogicalDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = {ChronoLogicalDateValidator.class})
public @interface ChronologicalDates {
	String message() default "{org.javaee7.cdi.constraints.ChronologicalDates.message}" ;
	Class<?>[] groups() default {} ;
	Class<? extends Payload>[] payload() default {} ;
}
