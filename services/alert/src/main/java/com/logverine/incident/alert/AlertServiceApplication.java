package com.logverine.incident.alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class AlertServiceApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		System.out.println("Timezone = " + java.util.TimeZone.getDefault().getID());
		SpringApplication.run(AlertServiceApplication.class, args);
	}
}
