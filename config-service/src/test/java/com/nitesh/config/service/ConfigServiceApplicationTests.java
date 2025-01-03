package com.nitesh.config.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest(classes = ConfigServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ConfigServiceApplicationTests {

	@Value("${local.server.port}")
	private int port = 0;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		mockMvc.perform(get("/actuator/health"))
				.andExpect(status().isOk());
	}

	@Test
	void testHealth() throws Exception {
		mockMvc.perform(get("/actuator/health"))
				.andExpect(status().isOk());
	}

	@Test
	void testDefault() throws Exception {
		mockMvc.perform(get("/some-app/default"))
				.andExpect(status().isOk());
	}

	@Test
	void testFetchInvalidApplication() throws Exception {
		mockMvc.perform(get("/nonexistent-app/nonexistent-profile"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.propertySources").isEmpty());
	}

	@Test
	void testGatewayDefault() throws Exception {
		mockMvc.perform(get("/gateway-service/default"))
				.andExpect(status().isOk());
	}

}
