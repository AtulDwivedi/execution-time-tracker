package com.atuldwivedi.starter.execution.time.tracker.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Atul Dwivedi
 */
@ConfigurationProperties(
        prefix = "execution-time-tracker"
)
@Data
public class ExecutionTimeTrackerProperties {

    private final Log log = new Log();

    @Data
    public static class Log {
        private String level;
    }
}
