package com.practice.splitexpense.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Transaction {
    private Long from;
    private Long to;
    private double amount;
}
