package com.phoenixtype.KafkaStreamService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;
//import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class KafkaStreamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamServiceApplication.class, args);
	}

}
