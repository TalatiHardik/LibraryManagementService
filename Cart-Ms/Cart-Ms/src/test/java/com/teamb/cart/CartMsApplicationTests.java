package com.teamb.cart;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * tests user's cart
 * @author team-b
 * @version latest
 */
@SpringBootTest
@AutoConfigureMockMvc
class CartMsApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	//token used for testing 
	private static final String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJzaWQiLCJleHAiOjE2MzcxODE2MDUsImlhdCI6MTYzNjMxNzYwNX0.97gWhBVG0UgDsgYvdTW9EC1WOhpr7PtJIrs9TjxEyZkzU0o0uZtD5tDeWRfF3yL5";
	
	@Test
	void contextLoads() {
	}

	/**
	 * tests adding available book into the cart with a valid token
	 * @throws Exception
	 */
	@Test
	void testAddToCart() throws Exception {
		mockMvc.perform(post("/add-to-cart/sid/The wish").header("Authorization", "Bearer "+token))
			.andExpect(status().isAccepted());
	}
	
	/**
	 * tests adding unavailable book into the cart with a valid token
	 * @throws Exception
	 */
	@Test
	void NtestAddToCart() throws Exception {
		mockMvc.perform(post("/add-to-cart/sid/The wish-2").header("Authorization", "Bearer "+token))
			.andExpect(status().isBadRequest());
	}
	
	/**
	 * tests adding available book into the cart with an invalid token
	 * @throws Exception
	 */
	@Test
	void NtestAddToCart2() throws Exception {
		mockMvc.perform(post("/add-to-cart/sid/The wish-2").header("Authorization", "Bearer"+token))
			.andExpect(status().isBadRequest());
	}
	
	/**
	 * tests removing book from the cart with a valid token
	 * @throws Exception
	 */
	@Test
	void testRemoveFromCart() throws Exception {
		mockMvc.perform(post("/add-to-cart/sid/The wish").header("Authorization", "Bearer "+token));
		mockMvc.perform(delete("/remove-book/sid/The wish").header("Authorization", "Bearer "+token))
			.andExpect(status().isAccepted());
	}
	
	/**
	 * tests removing book from the cart with an invalid token
	 * @throws Exception
	 */
	@Test
	void NtestRemoveFromCart() throws Exception {
		mockMvc.perform(post("/add-to-cart/sid/The wish").header("Authorization", "Bearer "+token));
		mockMvc.perform(delete("/remove-book/sid/The wish").header("Authorization", "Bearer"+token))
			.andExpect(status().isBadRequest());
	}
	
	/**
	 * tests retrieving unavailable book from the cart with a valid token
	 * @throws Exception
	 */
	@Test
	void NtestGetBookDetails() throws Exception {
		mockMvc.perform(get("/find-by-name/The wish-2").header("Authorization", "Bearer "+token))
			.andExpect(status().isNotFound());
	}
	
}
