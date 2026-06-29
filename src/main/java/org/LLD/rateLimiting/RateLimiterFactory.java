package org.LLD.rateLimiting;

import org.LLD.rateLimiting.config.RateLimiterConfig;
import org.LLD.rateLimiting.config.RateLimiterType;
import org.LLD.rateLimiting.interfaces.RateLimiter;
import org.LLD.rateLimiting.strategy.SlidingWindowRateLimiter;
import org.LLD.rateLimiting.strategy.TokenBucketRateLimiter;

public class RateLimiterFactory {
    public static RateLimiter get(RateLimiterType rateLimiterType, RateLimiterConfig rateLimiterConfig){
        return switch (rateLimiterType){
            case TOKEN_BUCKET -> new TokenBucketRateLimiter(rateLimiterConfig);
            case FIXED -> new TokenBucketRateLimiter(rateLimiterConfig);
            case SLIDING_WINDOW -> new SlidingWindowRateLimiter(rateLimiterConfig);

        };

    }
}
