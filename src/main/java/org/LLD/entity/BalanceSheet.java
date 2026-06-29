package org.LLD.entity;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class BalanceSheet {
    Long id;
    int totalPaid;
    int totalExpense;
    Map<User,Double> balances;

}
