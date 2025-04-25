package com.assignment.shakespeare.shakespeare;

import com.assignment.shakespeare.shakespeare.configuration.ApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableConfigurationProperties(ApplicationConfiguration.class)
class ShakespeareApplicationTests {

	@Test
	void contextLoads() {
	}

}
