package com.codechamps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SensorDataConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorDataConsumerApplication.class, args);
		System.setProperty("java.awt.headless", "false");
	}
}
