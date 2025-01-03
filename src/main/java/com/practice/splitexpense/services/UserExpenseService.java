package com.practice.splitexpense.services;

import com.practice.splitexpense.models.UserExpense;
import com.practice.splitexpense.repositories.UserExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserExpenseService {
    private UserExpenseRepository userExpenseRepository;
    public UserExpense addUserExpense(UserExpense userExpense)
    {
       return userExpenseRepository.save(userExpense);
    }
}
