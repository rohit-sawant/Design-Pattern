package org.designPatterns.observer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EmailNotificationObserver implements  Observer<Integer>{

    String email;

    public EmailNotificationObserver(String email) {
        this.email = email;
    }


    public void sendEmail(){
        System.out.println("Email sent to "+email);
    }

    @Override
    public void update(Integer o) {
        sendEmail();
    }
}
