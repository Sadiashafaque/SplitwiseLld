package com.practice.splitexpense.services;

import com.practice.splitexpense.dtos.CreateExpenseDto;
import com.practice.splitexpense.dtos.CreateUserExpenseDto;
import com.practice.splitexpense.dtos.Transaction;
import com.practice.splitexpense.exceptions.ExpenseNotFoundException;
import com.practice.splitexpense.models.Expense;
import com.practice.splitexpense.models.ExpenseStatus;
import com.practice.splitexpense.models.User;
import com.practice.splitexpense.models.UserExpense;
import com.practice.splitexpense.repositories.ExpenseRepository;
import com.practice.splitexpense.repositories.UserRepository;
import com.practice.splitexpense.strategies.SettleUpStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService {
    private final UserRepository userRepository;
    private UserExpenseService userExpenseService;
    private UserService userService;
    private ExpenseRepository expenseRepository;
    private SettleUpStrategy settleUpStrategy;
    public Expense createExpense(CreateExpenseDto createExpenseDto)
    {
        //1. get userid objects of all users
        //2. create expense object from dto
        //3. save in expense repo
        List<User> users = userService.getUsers(createExpenseDto.getUserIds());
        Expense expense = Expense.builder()
                .amount(createExpenseDto.getAmount())
                .description(createExpenseDto.getDescription())
                .status(ExpenseStatus.PENDING)
                .users(users)
                .build();

        return expenseRepository.save(expense);
    }
    public UserExpense addUserExpense(CreateUserExpenseDto createUserExpenseDto)
    {
        Expense expense = expenseRepository.findById(createUserExpenseDto.getExpenseId()).
                orElseThrow(()-> new ExpenseNotFoundException(createUserExpenseDto.getExpenseId()));
        User user = userService.getUser(createUserExpenseDto.getUserId());
        UserExpense userExpense = UserExpense.builder()
                .amount(createUserExpenseDto.getAmount())
                .expense(expense)
                .user(user)
                .type(createUserExpenseDto.getExpenseType())
                .build();
        return userExpenseService.addUserExpense(userExpense);
    }

    public List<Transaction> settleUpSingleUser(Long userId)
    {
        //find all users coresponding to thel list of userids
        User user = userService.getUser(userId);
        //find all userexpense for this user
        List<UserExpense> userExpenses = userExpenseService.getallUserExpense(user);
        System.out.println("userexpense are: " + userExpenses.size());
        List<Expense> expenses = new ArrayList<>();
        for(UserExpense userExpense: userExpenses)
        {
            expenses.add(userExpense.getExpense());
        }
        expenses = new ArrayList<>(new HashSet<>(expenses));
        System.out.println("expense are: " + expenses.size());
        List<Transaction> alltransactions = settleUpStrategy.settleUp(expenses);
        List<Transaction> filteredTransactions = new ArrayList<>();
        //now filter only for those which s for given userid
        for(Transaction transaction: alltransactions)
        {
            if(transaction.getFrom().equals(userId) || transaction.getTo().equals(userId))
            {
                filteredTransactions.add(transaction);
            }
        }
        System.out.println("settle up expense service done successfully");
        return filteredTransactions;
    }

//    public List<Transaction> settleUp(List<Long> userIds)
//    {
//        //find all users coresponding to thel list of userids
//        List<User> users = userService.getUsers(userIds);
//        //
//        List<UserExpense> userExpenses = userExpenseService.getallUserExpense(users);
//        List<Expense> expenses = new ArrayList<>();
//        for(UserExpense userExpense: userExpenses)
//        {
//            expenses.add(userExpense.getExpense());
//        }
//        List<Transaction> alltransactions = settleUpStrategy.settleUp(expenses);
//        List<Transaction> filteredTransactions = new ArrayList<>();
//        //now filter only for those which s for given userid
//        for(Transaction transaction: alltransactions)
//        {
//            if(transaction.getFrom().equals(userId) || transaction.getTo().equals(userId))
//            {
//                filteredTransactions.add(transaction);
//            }
//        }
//        return filteredTransactions;
//    }
}
