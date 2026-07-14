package com.logverine.incident.incident;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.TimeZone;

@Disabled
@SpringBootTest
@ActiveProfiles("test")
class IncidentServiceApplicationTests {

	// TODO: Replace with environment/JVM timezone configuration
	static {
		System.setProperty("user.timezone", "Asia/Kolkata");
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
	}

	@Test
	void contextLoads() {
	}

}
