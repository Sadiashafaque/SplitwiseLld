package com.practice.splitexpense.commands;

import com.practice.splitexpense.controller.ExpenseController;
import com.practice.splitexpense.dtos.CreateExpenseDto;
import com.practice.splitexpense.models.Expense;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class CreateExpenseCommand implements Command{
    private ExpenseController expenseController;
    @Override
    public boolean matches(String input) {
        if(Command.getCommand(input).equals(Commands.CREATE_EXPENSE_COMMAND))
        {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        // create-expense dexception amount users
        //create-expense fhfhfhfhhfh 1000 1,2,3
        //1. tokenise the input
        //2.get the users list from token[3]
        //3. create dto for createexpensedto
        //4. call the createexpense controller with the dto
        System.out.println("Executing create expense command");
        List<String> tokens = Command.getTokens(input);
        List<Long> userIds = Arrays.stream(tokens.get(3).split(","))
                .map(Long::valueOf)
                .toList();
        CreateExpenseDto request = CreateExpenseDto.builder().amount(Double.valueOf(tokens.get(2)))
                .userIds(userIds)
                .description(tokens.get(1))
                .build();
        Expense expense = expenseController.CreateExpense(request);
        System.out.println("Created expense with id: " + expense.getId());
    }
}
