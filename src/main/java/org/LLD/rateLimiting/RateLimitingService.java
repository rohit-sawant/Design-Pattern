package org.LLD.rateLimiting;

import org.LLD.entity.User;
import org.LLD.rateLimiting.config.RateLimitWindow;
import org.LLD.rateLimiting.config.RateLimiterConfig;
import org.LLD.rateLimiting.config.RateLimiterType;
import org.LLD.rateLimiting.interfaces.RateLimiter;

import static java.lang.Thread.sleep;

public class RateLimitingService {
    public static void main(String[] args) throws InterruptedException {
        User user1 = new User(1l,"Rohit","rohit.sawant@gmail.com");
        User user2 = new User(1l,"Rohan","rohit.sawant@gmail.com");

        RateLimiterConfig config = new RateLimiterConfig(10, RateLimitWindow.SECOND,1);
        RateLimiter rateLimiter = RateLimiterFactory.get(RateLimiterType.TOKEN_BUCKET,config);

        for (int i = 0; i < 100000; i++) {

            if(rateLimiter.isAllowed(user1)){

                System.out.println(i+" request");
            }


        }
//        System.out.println(rateLimiter.isAllowed(user1));
//
//        System.out.println(rateLimiter.isAllowed(user1));
//
//        System.out.println(rateLimiter.isAllowed(user1));
//        System.out.println(rateLimiter.isAllowed(user1));
//
//        System.out.println(rateLimiter.isAllowed(user1));
//
//        System.out.println(rateLimiter.isAllowed(user1));
//
//        System.out.println(rateLimiter.isAllowed(user1));
//
//        System.out.println(rateLimiter.isAllowed(user1));
    }
}
