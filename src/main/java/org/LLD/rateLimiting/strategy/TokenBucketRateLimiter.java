package org.LLD.rateLimiting.strategy;

import org.LLD.entity.Pair;
import org.LLD.entity.User;
import org.LLD.rateLimiting.config.RateLimiterConfig;
import org.LLD.rateLimiting.interfaces.RateLimiter;

import java.security.KeyPair;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

//config : 1 min 6 request


public class TokenBucketRateLimiter extends RateLimiter {
    Map<Long,Integer> userToTokenCounter = new ConcurrentHashMap<>();
    Map<Long,Long> userToLastTokenUpdated = new ConcurrentHashMap<>();


    //    1 2 3 4 5 6 7 8 9 10 11 12
    public TokenBucketRateLimiter(RateLimiterConfig rateLimiterConfig) {
        this.config = rateLimiterConfig;


    }

    @Override
    public synchronized  boolean isAllowed(User user) {
        Long userId = user.getId();
    AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis();
        userToLastTokenUpdated.putIfAbsent(userId,now);
        userToTokenCounter.compute(userId, (id, availableTokens) -> {
        int currentTokens = refillTokens(user.getId(), now);

        if (currentTokens > 0) {
            allowed.set(true);           // mark allowed BEFORE we decrement
            return currentTokens - 1;    // consume 1 token
        } else {
            return currentTokens;        // remain at 0
        }
    });

        return allowed.get();
}

// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
// free user refill rate = 60 / 10 = 6
private int refillTokens(Long userId, long now) {
    double refillRate = config.getNoOfRequestAllowed()/(double) config.getValue() ;

    long lastRefill = userToLastTokenUpdated.getOrDefault(userId, now);
    long elapsedSeconds = (now - lastRefill) / 1000;

    int refillTokens = (int) (elapsedSeconds / refillRate);

    int currentTokens = userToTokenCounter.getOrDefault(userId, config.getNoOfRequestAllowed());
    currentTokens = Math.min(config.getNoOfRequestAllowed(), currentTokens + refillTokens);
    if (refillTokens > 0) {
        userToLastTokenUpdated.put(userId, now);
        System.out.println(userId + " "+now+" "+userToLastTokenUpdated.getOrDefault(userId,now)+" "+elapsedSeconds);

    }

    return currentTokens;
}


//    @Override
//     synchronized public boolean isAllowed(User user) {
//        long now = System.currentTimeMillis();
////        synchronized (user){
//           if(!userToTokenCounter.containsKey(user.getId())){
//               userToTokenCounter.put(user.getId(), config.getNoOfRequestAllowed());
//               userToLastTokenUpdated.put(user.getId(),now);
//               return true;
//           }
//           else{
//               int currentToken = refillTokens(user.getId(),now);
//               if(currentToken>0){
//                   userToTokenCounter.put(user.getId(), --currentToken);
//                    return true;
//               }
//
//           }
//
//        return false;
//
////        }
//    }
//    private int refillTokens(Long userId, long now) {
//
//
//        int currentNoOfToken = userToTokenCounter.get(userId);
//
//        double timeTakenToFillOneTokenIn1Sec = (double) (config.getValue()) /config.getNoOfRequestAllowed();
//
//        long elapsedSeconds =  ((now-userToLastTokenUpdated.get(userId))/1000);
//
//        int noOfTokens = (int) (elapsedSeconds / timeTakenToFillOneTokenIn1Sec);
//        if(noOfTokens>0){
//            System.out.println("token added");
//            userToTokenCounter.put(userId,Math.min(config.getNoOfRequestAllowed(),currentNoOfToken+noOfTokens));
//            userToLastTokenUpdated.put(userId,now);
//        }
//
//        return currentNoOfToken;
//    }
}
