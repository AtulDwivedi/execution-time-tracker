package com.atuldwivedi.starter.execution.time.tracker.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Atul Dwivedi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TrackExecutionTime {

    String logMessagePrefix() default "";

    String logLevel() default "";
}
