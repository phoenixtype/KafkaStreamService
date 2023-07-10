package com.phoenixtype.KafkaStreamService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private String key_schema;
    private int value_schema_id;
    private List<Record<Customer>> records;
}