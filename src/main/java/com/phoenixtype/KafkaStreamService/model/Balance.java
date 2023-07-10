package com.phoenixtype.KafkaStreamService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Balance {
    private String balanceId;
    private String accountId;
    private double balance;
}
