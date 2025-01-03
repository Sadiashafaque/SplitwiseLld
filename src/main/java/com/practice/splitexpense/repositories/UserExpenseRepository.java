package com.practice.splitexpense.repositories;

import com.practice.splitexpense.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
}
