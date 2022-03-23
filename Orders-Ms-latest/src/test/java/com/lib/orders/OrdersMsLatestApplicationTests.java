package com.lib.orders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * tests user's orders
 * @author team-b
 * @version latest
 */
@SpringBootTest
@AutoConfigureMockMvc
class OrdersMsLatestApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	//token used for testing 
	private static final String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJzaWQiLCJleHAiOjE2MzcxODE2MDUsImlhdCI6MTYzNjMxNzYwNX0.97gWhBVG0UgDsgYvdTW9EC1WOhpr7PtJIrs9TjxEyZkzU0o0uZtD5tDeWRfF3yL5";

	@Test
	void contextLoads() {
	}
	
	/**
	 * tests processing of user's order with a valid token
	 * @throws Exception
	 */
	@Test
	void testProcessOrder() throws Exception {
		mockMvc.perform(post("/orders/process-order/sid").header("Authorization", "Bearer "+token))
			.andExpect(status().isCreated());
	}
	
	/**
	 * tests processing of user's order with a invalid token
	 * @throws Exception
	 */
	@Test
	void NtestProcessOrder() throws Exception {
		mockMvc.perform(post("/orders/process-order/sid").header("Authorization", "Bearer"+token))
			.andExpect(status().isForbidden());
	}

	/**
	 * tests retrieving user's orders with a valid token
	 * @throws Exception
	 */
	@Test
	void testGetAllBooks() throws Exception {
		mockMvc.perform(get("/orders/get-all-books-ordered/sid").header("Authorization", "Bearer "+token))
			.andExpect(status().isOk());
	}
	
	/**
	 * tests retrieving user's orders with a invalid token
	 * @throws Exception
	 */
	@Test
	void NtestGetAllBooks() throws Exception {
		mockMvc.perform(get("/orders/get-all-books-ordered/sid").header("Authorization", "Bearer"+token))
			.andExpect(status().isForbidden());
	}
	
	/**
	 * tests retrieving user's orders with a valid token
	 * @throws Exception
	 */
	
	/**
	 * tests retrieving user's orders with a invalid token
	 * @throws Exception
	 */
	@Test
	void NtestGetAllOrders() throws Exception {
		mockMvc.perform(get("/orders/get-all-orders/sid").header("Authorization", "Bearer"+token))
		.andExpect(status().isNotFound());
	}
	
}
