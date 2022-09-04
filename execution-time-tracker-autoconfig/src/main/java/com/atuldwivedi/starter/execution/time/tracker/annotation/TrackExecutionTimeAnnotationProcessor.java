package com.atuldwivedi.starter.execution.time.tracker.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author Atul Dwivedi
 */
public class TrackExecutionTimeAnnotationProcessor {

    public static String getMethodLogLevel(ProceedingJoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        if (methodSignature == null) {
            return null;
        }

        Method method = methodSignature.getMethod();
        if (method == null) {
            return null;
        }

        TrackExecutionTime trackExecutionTime = method.getAnnotation(TrackExecutionTime.class);
        if (trackExecutionTime == null) {
            return null;
        }

        String logLevel = trackExecutionTime.logLevel();
        if ("".equals(logLevel)) {
            return null;
        }

        return logLevel;
    }

    public static String getMethodLogPrefix(ProceedingJoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        if (methodSignature == null) {
            return null;
        }

        Method method = methodSignature.getMethod();
        if (method == null) {
            return null;
        }

        TrackExecutionTime trackExecutionTime = method.getAnnotation(TrackExecutionTime.class);
        if (trackExecutionTime == null) {
            return null;
        }

        String logMessagePrefix = trackExecutionTime.logMessagePrefix();
        if ("".equals(logMessagePrefix)) {
            return null;
        }

        return logMessagePrefix;
    }
}
