package org.javaee7.cdi.annotations;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE,METHOD})
public @interface Sanitize {
}
