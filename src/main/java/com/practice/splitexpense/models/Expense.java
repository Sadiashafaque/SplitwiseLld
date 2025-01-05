package com.practice.splitexpense.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Expense extends BaseModel {

    private String description;
    private Double amount;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "expense", fetch = FetchType.EAGER)
    private List<UserExpense> paidBy = new ArrayList<>();

    @OneToMany(mappedBy = "expense", fetch = FetchType.EAGER)
    private List<UserExpense> owedBy = new ArrayList<>(); //actual share

    @Enumerated
    private ExpenseStatus status;
}

// E1 - Paid  - A 100 B 200
//    - Owed  - A 150 B 150

// E1 => UE1 A 100 PAID
// E1 => UE2 B 200 PAID

// E1 => UE3 A 150 OWED
// E1 => UE4 A 150 OWED

// E2 => UE1

