package com.practice.splitexpense.strategies;

import com.practice.splitexpense.dtos.Transaction;
import com.practice.splitexpense.models.Expense;

import java.util.List;

public interface SettleUpStrategy {
    public List<Transaction> settleUp(List<Expense> e);
}
