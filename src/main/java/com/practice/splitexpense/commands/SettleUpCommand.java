package com.practice.splitexpense.commands;

import com.practice.splitexpense.controller.ExpenseController;
import com.practice.splitexpense.dtos.CreateUserExpenseDto;
import com.practice.splitexpense.dtos.SettleUpRequestDto;
import com.practice.splitexpense.dtos.Transaction;
import com.practice.splitexpense.models.ExpenseType;
import com.practice.splitexpense.models.UserExpense;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SettleUpCommand implements Command{
    private ExpenseController expenseController;
    @Override
    public boolean matches(String input) {
        if (Command.getCommand(input).equals(Commands.SETTLE_UP_COMMAND)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        //settle userid
        System.out.println("Executing settle up command");
        List<String> tokens = Command.getTokens(input);
        SettleUpRequestDto settleUpRequestDto = SettleUpRequestDto.builder()
                .userId(Long.valueOf(tokens.get(1)))
                .build();
        List<Transaction> transactions = expenseController.settleUpUser(settleUpRequestDto.getUserId());
        System.out.println("List of all transaction to settle " + settleUpRequestDto.getUserId() + " is: " + transactions);
    }
}
