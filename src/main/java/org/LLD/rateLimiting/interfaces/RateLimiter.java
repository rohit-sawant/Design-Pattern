package org.LLD.rateLimiting.interfaces;

import org.LLD.entity.User;
import org.LLD.rateLimiting.config.RateLimiterConfig;
import org.LLD.rateLimiting.config.RateLimiterType;

public abstract class RateLimiter {
    protected   RateLimiterConfig config;
    public abstract boolean isAllowed(User user);

    public RateLimiterConfig getConfig() {
        return config;
    }
}
