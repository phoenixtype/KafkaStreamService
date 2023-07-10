package com.phoenixtype.KafkaStreamService.service;

import com.phoenixtype.KafkaStreamService.model.Balance;
import com.phoenixtype.KafkaStreamService.model.Customer;
import com.phoenixtype.KafkaStreamService.model.CustomerBalance;
//import io.confluent.kafka.streams.serdes.avro.SpecificAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaStreamsService {

    private final KafkaTemplate<String, CustomerBalance> kafkaTemplate;

    @Autowired
    public KafkaStreamsService(KafkaTemplate<String, CustomerBalance> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = {"customer", "balance"}, groupId = "customer-balance-group")
    public void processKafkaStreams(ConsumerRecord<String, Customer> customerRecord, ConsumerRecord<String, Balance> balanceRecord) {
        Customer customer = customerRecord.value();
        Balance balance = balanceRecord.value();

        CustomerBalance customerBalance = new CustomerBalance(
                customer.getAccountId(),
                customer.getCustomerId(),
                customer.getPhoneNumber(),
                balance.getBalance()
        );
        kafkaTemplate.send("customer-balance", customerBalance.getAccountId(), customerBalance);
    }

    @KafkaListener(topics = "customer-balance", groupId = "customer-balance-group")
    public void consumeCustomerBalance(ConsumerRecord<String, CustomerBalance> record) {
        CustomerBalance customerBalance = record.value();
        System.out.println("Received CustomerBalance: " + customerBalance);
    }
}
