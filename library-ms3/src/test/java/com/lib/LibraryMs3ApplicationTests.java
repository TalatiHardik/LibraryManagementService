package com.lib;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * tests inventory of library
 * @author team-b
 * @version latest
 */
@SpringBootTest
@AutoConfigureMockMvc
class LibraryMs3ApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	//token used for testing 
	private static final String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJzaWQiLCJleHAiOjE2MzcxODE2MDUsImlhdCI6MTYzNjMxNzYwNX0.97gWhBVG0UgDsgYvdTW9EC1WOhpr7PtJIrs9TjxEyZkzU0o0uZtD5tDeWRfF3yL5";
	
	@Test
	void contextLoads() {
	}
	
	/**
	 * tests getFullBooks method with a valid token
	 * @throws Exception
	 */
	@Test
	void testGetBooks() throws Exception {
		mockMvc.perform(get("/books/get-all-books"))
				.andExpect(status().isOk());
	}
	
	
	/**
	 * tests findById method passing valid book id as argument
	 * @throws Exception
	 */
	@Test
	void testFindBookById() throws Exception {
		mockMvc.perform(get("/books/bookName/The wish").header("Authorization", "Bearer "+token)).andExpect(status().isOk());
	}
	
	/**
	 * tests findById method passing invalid book id as argument
	 * @throws Exception
	 */
	@Test
	void NtestFindBookById() throws Exception {
		mockMvc.perform(get("/books/bookName/The wish-2").header("Authorization", "Bearer "+token)).andExpect(status().isNotFound());
	}

}
