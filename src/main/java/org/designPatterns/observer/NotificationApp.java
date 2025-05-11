package org.designPatterns.observer;

public class NotificationApp {
    public static void main(String[] args) {

        IphoneObservableImpl iphoneObservable = new IphoneObservableImpl();

        Observer<Integer> emailNotificationObserver = new EmailNotificationObserver("abc@gmail.com");
        Observer<Integer> emailNotificationObserver1 = new EmailNotificationObserver("abc2@gmail.com");
        Observer<Integer> mobileNotificationObserver = new MobileNotificationObserver("8291520420");

        iphoneObservable.add(emailNotificationObserver);
        iphoneObservable.add(emailNotificationObserver1);
        iphoneObservable.add(mobileNotificationObserver);

        iphoneObservable.setData(10);
        iphoneObservable.setData(20);

    }
}
