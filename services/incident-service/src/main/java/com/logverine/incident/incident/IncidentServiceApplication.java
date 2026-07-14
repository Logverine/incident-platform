package com.logverine.incident.incident;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class IncidentServiceApplication {

	// TODO: Replace with environment/JVM timezone configuration
	static {
		System.setProperty("user.timezone", "Asia/Kolkata");
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
	}

	public static void main(String[] args) {
		System.out.println("Default timezone = " + TimeZone.getDefault().getID());
		SpringApplication.run(IncidentServiceApplication.class, args);
	}
}
