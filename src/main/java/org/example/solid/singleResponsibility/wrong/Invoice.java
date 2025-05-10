package org.example.solid.singleResponsibility.wrong;

public interface Invoice {
    public void calculateTotal() ;
    public void saveToDatabase() ; // 🛑 unrelated responsibility
    public void printInvoice() ;   // 🛑 unrelated responsibility
}

