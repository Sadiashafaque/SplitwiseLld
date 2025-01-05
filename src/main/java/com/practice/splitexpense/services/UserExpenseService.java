package com.practice.splitexpense.services;

import com.practice.splitexpense.models.*;
import com.practice.splitexpense.repositories.UserExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserExpenseService {
    private UserExpenseRepository userExpenseRepository;
    public UserExpense addUserExpense(UserExpense userExpense)
    {

        return userExpenseRepository.save(userExpense);
    }
    public List<UserExpense> getallUserExpense(User user)
    {
        return userExpenseRepository.findAllByUser(user);
    }
//    public List<UserExpense> getallUserExpense(List<User> users)
//    {
//        return userExpenseRepository.findAllByUser(user);
//    }
}
