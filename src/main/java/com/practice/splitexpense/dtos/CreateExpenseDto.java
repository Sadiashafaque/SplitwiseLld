package com.practice.splitexpense.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class CreateExpenseDto {
    private String description;
    private Double amount;
    private List<Long> userIds = new ArrayList<>();
}
