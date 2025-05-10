package org.designPatterns.solid.singleResponsibility.wrong;

public interface Invoice {
    public void calculateTotal() ;
    public void saveToDatabase() ; // 🛑 unrelated responsibility
    public void printInvoice() ;   // 🛑 unrelated responsibility
}

