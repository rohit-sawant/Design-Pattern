package org.LLD.rateLimiting.config;

public class RateLimiterConfig {
    int noOfRequestAllowed;
    RateLimitWindow rateLimitWindow;
    int value;

    public RateLimiterConfig(int noOfRequestAllowed, RateLimitWindow rateLimitWindow, int value) {
        this.noOfRequestAllowed = noOfRequestAllowed;
        this.rateLimitWindow = rateLimitWindow;
        this.value = value;
    }

    public int getNoOfRequestAllowed() {
        return noOfRequestAllowed;
    }

    public RateLimitWindow getRateLimitWindow() {
        return rateLimitWindow;
    }

    public int getValue() {
        return value;
    }
}
