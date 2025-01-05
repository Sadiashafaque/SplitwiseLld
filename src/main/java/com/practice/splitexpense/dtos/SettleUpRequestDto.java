package com.practice.splitexpense.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SettleUpRequestDto {
    private Long userId;
}
