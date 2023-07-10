package com.phoenixtype.KafkaStreamService.service;

import com.phoenixtype.KafkaStreamService.model.Balance;
import com.phoenixtype.KafkaStreamService.model.Customer;
import com.phoenixtype.KafkaStreamService.model.CustomerBalance;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaStreamsService {

    private final KafkaTemplate<String, CustomerBalance> kafkaTemplate;

    @Autowired
    public KafkaStreamsService(StreamsBuilder streamsBuilder, KafkaTemplate<String, CustomerBalance> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = {"customer", "balance"})
    public void processKafkaStreams(Customer customer, Balance balance) {
        CustomerBalance customerBalance = new CustomerBalance(
                customer.getAccountId(),
                customer.getCustomerId(),
                customer.getPhoneNumber(),
                balance.getBalance()
        );

        kafkaTemplate.send("customer-balance", customerBalance.getAccountId(), customerBalance);
    }

    @KafkaListener(topics = "customer-balance")
    public void consumeCustomerBalance(CustomerBalance customerBalance) {
        System.out.println("Received CustomerBalance: " + customerBalance);
    }
}
