package org.LLD.entity;

import java.util.List;
import java.util.Map;


//1
//Jyoti
//Rohit
//Rishi



public class Group {
    Long id;
    List<User> users;
    int totalExpense;
    List<Expense> expenses;
    Map<User,BalanceSheet> balanceSheets;
}


