package com.practice.splitexpense.commands;

import com.practice.splitexpense.controller.ExpenseController;
import com.practice.splitexpense.dtos.CreateUserExpenseDto;
import com.practice.splitexpense.models.ExpenseType;
import com.practice.splitexpense.models.UserExpense;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CreateUserExpenseCommand implements Command{
    private ExpenseController expenseController;
    @Override
    public boolean matches(String input) {
        if (Command.getCommand(input).equals(Commands.ADD_USER_EXPENSE)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        //add-expense userid expenseid amout type
        System.out.println("Executing create user expense command");
        List<String> tokens = Command.getTokens(input);
        CreateUserExpenseDto createUserExpenseDto = CreateUserExpenseDto.builder()
                .userId(Long.valueOf(tokens.get(1)))
                .expenseId(Long.valueOf(tokens.get(2)))
                .amount(Double.valueOf(tokens.get(3)))
                .expenseType(ExpenseType.valueOf(tokens.get(4).toUpperCase()))
                .build();
        UserExpense userExpense = expenseController.addUserExpense(createUserExpenseDto);
        System.out.println("Created user expense with user id: " + userExpense.getId());
    }
}
