package org.designPatterns.factory.right;


public class NotificationSender {
    static {
        NotificationFactoryImpl.register("EMAIL", EmailNotification::new);
        NotificationFactoryImpl.register("SMS", SMSNotification::new);
    }

    public static void main(String[] args) {
        Notification n = NotificationFactoryImpl.createNotification("EMAIL");
        n.notifyUser();
    }
}
