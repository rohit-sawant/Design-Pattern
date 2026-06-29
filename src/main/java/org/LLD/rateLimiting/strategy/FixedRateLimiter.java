package org.LLD.rateLimiting.strategy;

import org.LLD.entity.User;
import org.LLD.rateLimiting.config.RateLimiterConfig;
import org.LLD.rateLimiting.interfaces.RateLimiter;

public class FixedRateLimiter extends RateLimiter {

    public FixedRateLimiter(RateLimiterConfig rateLimiterConfig) {
        this.config = rateLimiterConfig;
    }

    @Override
    public boolean isAllowed(User user) {
        return false;
    }
}
