package com.practice.splitexpense.services;

import com.practice.splitexpense.dtos.CreateExpenseDto;
import com.practice.splitexpense.dtos.CreateUserExpenseDto;
import com.practice.splitexpense.exceptions.ExpenseNotFoundException;
import com.practice.splitexpense.models.Expense;
import com.practice.splitexpense.models.ExpenseStatus;
import com.practice.splitexpense.models.User;
import com.practice.splitexpense.models.UserExpense;
import com.practice.splitexpense.repositories.ExpenseRepository;
import com.practice.splitexpense.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService {
    private final UserRepository userRepository;
    private UserExpenseService userExpenseService;
    private UserService userService;
    private ExpenseRepository expenseRepository;
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
}
