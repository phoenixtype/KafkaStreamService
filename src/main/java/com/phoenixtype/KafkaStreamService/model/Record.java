package com.phoenixtype.KafkaStreamService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record<T> {
    private String key;
    private T value;
}
