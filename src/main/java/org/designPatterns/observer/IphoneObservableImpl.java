package org.designPatterns.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IphoneObservableImpl implements  Observable<Integer>{

    public IphoneObservableImpl() {
    }

    List<Observer<Integer>> observers = new ArrayList<>();
    public int count =0;

    public void setData(Integer count){
        this.count = count;
        notifyObservers(count);
    }

    @Override
    public void add(Observer<Integer> observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer<Integer> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Integer data) {
        for(Observer<Integer> o:observers)
            o.update(data);
    }
}
