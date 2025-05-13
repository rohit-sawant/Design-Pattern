package org.designPatterns.factory.right;

public class EmailNotification implements Notification {
    public void notifyUser() {
        System.out.println("Email Notification");
    }
}

