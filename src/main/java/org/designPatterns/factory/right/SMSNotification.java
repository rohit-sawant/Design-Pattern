package org.designPatterns.factory.right;

public class SMSNotification implements Notification {
    public void notifyUser() {
        System.out.println("SMS Notification");
    }
}

