package com.lib.WalletMS;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * tests wallet microservice
 * @author team-b
 * @version latest
 */
@SpringBootTest
@AutoConfigureMockMvc
class WalletMsApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	//valid token used for testing
	private static final String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJzaWQiLCJleHAiOjE2MzcxODE2MDUsImlhdCI6MTYzNjMxNzYwNX0.97gWhBVG0UgDsgYvdTW9EC1WOhpr7PtJIrs9TjxEyZkzU0o0uZtD5tDeWRfF3yL5";

	@Test
	void contextLoads() {
	}

	/**
	 * tests getWallet functionality with a valid token
	 * @throws Exception
	 */
	@Test
	void testGetWallet() throws Exception {
		mockMvc.perform(get("/wallet/sid").header("Authorization", "Bearer " + token)).andExpect(status().isOk());
	}
	
	/**
	 * tests createWallet functionality with a valid token
	 * @throws Exception
	 */
	@Test
	void testCreateWallet() throws Exception {
		mockMvc.perform(post("/wallet/sid").header("Authorization", "Bearer " + token)).andExpect(status().isOk());
	}

	/**
	 * tests deductWallet functionality with a valid token
	 * @throws Exception
	 */
	@Test
	void testDeductWallet() throws Exception {
		mockMvc.perform(put("/wallet/deductWallet/sid/1/The wish/5").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}


	/**
	 * tests addToWallet functionality with a valid token
	 * @throws Exception
	 */
	@Test
	void testAddToWallet() throws Exception {
		mockMvc.perform(put("/wallet/addToWallet/sid/100").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}
	
}
