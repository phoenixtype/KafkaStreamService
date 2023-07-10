package com.phoenixtype.KafkaStreamService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBalance {
    private String accountId;
    private String customerId;
    private String phone;
    private double balance;
}
