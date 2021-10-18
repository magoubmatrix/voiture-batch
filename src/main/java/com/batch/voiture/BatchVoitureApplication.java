package com.batch.voiture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class BatchVoitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchVoitureApplication.class, args);
	}

}
