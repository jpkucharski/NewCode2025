package org.jpk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "retry")
public class RetryConfig {
    private int attempts;
    private int waitSecond;

    public int getAttempts() {
        return attempts;
    }

    public int getWaitSecond() {
        return waitSecond;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public void setWaitSecond(int waitSecond) {
        this.waitSecond = waitSecond;
    }
}
