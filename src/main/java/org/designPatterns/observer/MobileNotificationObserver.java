package org.designPatterns.observer;

public class MobileNotificationObserver implements  Observer<Integer>{

    String phone;

    public MobileNotificationObserver(String phone) {
        this.phone = phone;
    }

    @Override
    public void update(Integer o) {
        sendSMS();
    }
    public void sendSMS(){
        System.out.println("SMS sent to "+ phone);
    }
}
