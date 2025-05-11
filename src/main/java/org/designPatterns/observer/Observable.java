package org.designPatterns.observer;

public interface Observable<T>{
    void add(Observer<T> observer);
    void remove(Observer<T> observer);
    void notifyObservers(T data);
    void setData(T data);
}

