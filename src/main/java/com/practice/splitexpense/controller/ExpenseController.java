package com.practice.splitexpense.controller;

import com.practice.splitexpense.dtos.CreateExpenseDto;
import com.practice.splitexpense.dtos.CreateUserExpenseDto;
import com.practice.splitexpense.models.Expense;
import com.practice.splitexpense.models.UserExpense;
import com.practice.splitexpense.services.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ExpenseController {
    private ExpenseService expenseService;
    public Expense CreateExpense(CreateExpenseDto createExpenseDto)
    {
        return expenseService.createExpense(createExpenseDto);
    }
    public UserExpense addUserExpense(CreateUserExpenseDto createUserExpenseDto)
    {
        return expenseService.addUserExpense(createUserExpenseDto);
    }
}
