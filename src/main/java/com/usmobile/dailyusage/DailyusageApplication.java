package com.usmobile.dailyusage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DailyusageApplication {
	public static void main(String[] args) {
		SpringApplication.run(DailyusageApplication.class, args);
	}
}
