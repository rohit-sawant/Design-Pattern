package org.designPatterns.solid.singleResponsibility.right;

import java.util.List;

/**
 *
 * SRP is about purpose, not number of methods.
 * A class can have many methods, as long as they are all part of one responsibility.
 * If methods start addressing different concerns, it's time to refactor.
 */
public interface Invoice {
    public  List<String> items = List.of();
    public void addItem(String item) ;
    public void removeItem(String item);
    public double calculateTotal();
}
