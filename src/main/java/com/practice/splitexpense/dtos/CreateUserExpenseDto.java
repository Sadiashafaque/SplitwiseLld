package com.practice.splitexpense.dtos;

import com.practice.splitexpense.models.ExpenseType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateUserExpenseDto {
    private long userId;
    private long expenseId;
    private double amount;
    private ExpenseType expenseType;
}
