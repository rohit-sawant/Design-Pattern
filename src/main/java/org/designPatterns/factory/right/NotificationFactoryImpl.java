package org.designPatterns.factory.right;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class NotificationFactoryImpl {
    private static final Map<String, Supplier<Notification>> registry = new HashMap<>();

    public static void register(String type, Supplier<Notification> creator) {
        registry.put(type, creator);
    }

    public static Notification createNotification(String type) {
        Supplier<Notification> creator = registry.get(type);
        if (creator == null) {
            throw new IllegalArgumentException("Unknown notification type: " + type);
        }
        return creator.get();
    }
}