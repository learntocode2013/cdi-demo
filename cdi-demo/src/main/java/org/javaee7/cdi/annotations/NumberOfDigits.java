package org.javaee7.cdi.annotations;

import org.javaee7.cdi.Digit;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.*;

@Qualifier
@Retention(RUNTIME)
@Target({TYPE,FIELD,METHOD})
public @interface NumberOfDigits {
	Digit value();
	boolean odd();
}
