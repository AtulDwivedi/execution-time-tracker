package com.atuldwivedi.starter.execution.time.tracker.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@ConditionalOnProperty(
        prefix = "execution-time-tracker",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = false
)
@Import({ExecutionTimeTrackerAspectConfiguration.class})
@EnableConfigurationProperties({ExecutionTimeTrackerProperties.class})
public class ExecutionTimeTrackerAutoConfiguration {
}
