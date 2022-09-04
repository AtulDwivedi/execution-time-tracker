package com.atuldwivedi.starter.execution.time.tracker.autoconfigure;

import com.atuldwivedi.starter.execution.time.tracker.annotation.TrackExecutionTimeAnnotationProcessor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static com.atuldwivedi.starter.execution.time.tracker.annotation.TrackExecutionTimeAnnotationProcessor.*;

/**
 * @author Atul Dwivedi
 */
@Slf4j
@Configuration
@Aspect
@EnableAspectJAutoProxy
class ExecutionTimeTrackerAspectConfiguration {

    private final ExecutionTimeTrackerProperties executionTimeTrackerProperties;

    public ExecutionTimeTrackerAspectConfiguration(ExecutionTimeTrackerProperties executionTimeTrackerProperties) {
        this.executionTimeTrackerProperties = executionTimeTrackerProperties;
        System.out.println(executionTimeTrackerProperties);
    }

    @Around("@annotation(com.atuldwivedi.starter.execution.time.tracker.annotation.TrackExecutionTime)")
    public Object executionTimeTracker(ProceedingJoinPoint point) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endTime = System.currentTimeMillis();

        try {
            long executionTime = (endTime - startTime);
            String logLevel = getLogLevel(point);
            String logPrefix = getLogPrefix(point);
            logExecutionTime(logLevel, logPrefix, executionTime);
        } catch (Exception e) {
            log.warn("Failed to log execution time.");
        }

        return object;
    }

    private String getLogLevel(ProceedingJoinPoint point) {

        String methodLogLevel = getMethodLogLevel(point);
        if (methodLogLevel != null && !"".equals(methodLogLevel)) {
            return methodLogLevel;
        }

        String globalLogLevel = getGlobalLogLevel();
        if (globalLogLevel != null) {
            return globalLogLevel;
        }

        return "info";
    }

    private String getLogPrefix(ProceedingJoinPoint point) {

        String methodLogLevel = getMethodLogPrefix(point);
        if (methodLogLevel != null && !"".equals(methodLogLevel)) {
            return methodLogLevel;
        }

        String globalLogLevel = getGlobalLogPrefix();
        if (globalLogLevel != null) {
            return globalLogLevel;
        }

        String declaringTypeName = point.getSignature().getDeclaringTypeName();
        String name = point.getSignature().getName();

        return declaringTypeName + "." + name + ": Time taken for Execution is : ";
    }

    private String getGlobalLogPrefix() {
        return null;
    }

    private String getGlobalLogLevel() {
        if (executionTimeTrackerProperties == null
                || executionTimeTrackerProperties.getLog() == null
                || executionTimeTrackerProperties.getLog().getLevel() == null) {
            return "info";
        }

        return executionTimeTrackerProperties.getLog().getLevel();
    }

    private void logExecutionTime(String logLevel, String logPrefix, long executionTime) {
        if (logLevel == null) {
            return;
        }

        if (logLevel.equalsIgnoreCase("info")) {
            log.info(logPrefix + (executionTime) + "ms");
        } else if (logLevel.equalsIgnoreCase("trace")) {
            log.trace(logPrefix + (executionTime) + "ms");
        } else if (logLevel.equalsIgnoreCase("debug")) {
            log.debug(logPrefix + (executionTime) + "ms");
        } else if (logLevel.equalsIgnoreCase("warn")) {
            log.warn(logPrefix + (executionTime) + "ms");
        } else if (logLevel.equalsIgnoreCase("error")) {
            log.error(logPrefix + (executionTime) + "ms");
        }
    }
}
