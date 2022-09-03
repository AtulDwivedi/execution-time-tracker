package com.atuldwivedi.starter.execution.time.tracker.autoconfig;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@AutoConfiguration
@ConditionalOnProperty(
        prefix = "execution-time-tracker.log",
        name = "enabled",
        havingValue = "true", matchIfMissing = false
)
@Aspect
@EnableAspectJAutoProxy
public class ExecutionTimeTrackerAutoConfiguration {

    @Around("@annotation(com.atuldwivedi.starter.execution.time.tracker.annotation.TrackExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endtime = System.currentTimeMillis();
        log.info(point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + ": Time taken for Execution is : " + (endtime - startTime) + "ms");
        return object;
    }
}
