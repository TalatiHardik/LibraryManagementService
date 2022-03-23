package com.lib.authorization;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.srikanth.authorization.exceptions.UserExistsWithTheGivenCredentialsException;
//import com.srikanth.authorization.model.AuthenticationRequest;
//import com.srikanth.authorization.model.User;
//import com.srikanth.authorization.repository.UserRepository;
//import com.srikanth.authorization.service.MyUserDetailsService;
//import com.srikanth.authorization.user.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class AuthorizationServiceApplicationTests {

/*	@Mock
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MockMvc mockMvc;

	private MockHttpServletResponse response;

	@Test
	@Order(1)
	void loadByUserNameMethodTest() {
		User user = new User();
		user.setEmail("srikanth");
		user.setPwd("srikanth7977");
		log.info("creating user in ---> loadByUserNameMethodTest");
		UserDetails userDetails = new UserPrincipal(user);
		log.info("creating userDetails in ---> loadByUserNameMethodTest ");
		when(myUserDetailsService.loadUserByUsername("srikanth")).thenReturn(userDetails);
		assertNotNull(userDetails);
		log.info("Testing  userDetails object is not null in ---> loadByUserNameMethodTest ");
		assertEquals("srikanth", myUserDetailsService.loadUserByUsername("srikanth").getUsername());
		log.info("End of Testing userDetails by userName in ---> loadByUserNameMethodTest ");

	}

	@Test
	@Order(2)
	void loadByUserNameMethodForInavlidUserTest() {
		log.info("Testing the method with Exception in ---> loadByUserNameMethodForInavlidUserTest");
		when(myUserDetailsService.loadUserByUsername("shivaji"))
				.thenThrow(new UsernameNotFoundException("User not found"));
		assertThrows(UsernameNotFoundException.class, () -> {
			myUserDetailsService.loadUserByUsername("shivaji");
		});
		log.warn("UsernameNotFoundException was raised in ----> loadByUserNameMethodForInavlidUserTest");
		log.info("End of Testing with UsernameNotFoundException in --->  loadByUserNameMethodForInavlidUserTest");

	}

	@Test
	@Order(3)
	void addingNewUserToDBTest() {
		log.info("starting to add new user to db with passsword encryption --->addingNewUserToDBTest");
		User user = new User();
		user.setEmail("shivaji");
		user.setPwd("shivaji");
		log.info("creating a new user to add to db in ---> addingNewUserToDBTest");
		when(myUserDetailsService.addUser(user)).thenReturn("Registration Successfull!!!");
		assertEquals("Registration Successfull!!!", myUserDetailsService.addUser(user));
		log.info("End of adding a new user to db in ---> addingNewUserToDBTest");
	}

	@Test
	@Order(4)
	void addingExistingUserToDBTest() {
		log.info("starting to add new user to db --->addingExistingUserToDBTest");
		User user = new User();
		user.setUserName("shivaji");
		user.setPassword("shivaji");
		log.info("creating a new user to add to db in ---> addingExistingUserToDBTest");
		when(myUserDetailsService.addUser(user)).thenThrow(new UserExistsWithTheGivenCredentialsException());
		assertThrows(UserExistsWithTheGivenCredentialsException.class, () -> {
			myUserDetailsService.addUser(user);
		});
		log.warn("UserExistsWithTheGivenCredentialsException was raised in ----> addingExistingUserToDBTest");
		log.info("End of Testing with UserExistsWithTheGivenCredentialsException in --->  addingExistingUserToDBTest");
	}

	@Test
	@Order(5)
	// @WithUserDetails("srikanth")
	void createAuthenticationTokenTest() throws Exception {
		log.info("Testing -token generation functionality ----> createAuthenticationTokenTest");
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("srikanth", "srikanth7977");

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(authenticationRequest);
		log.info("creating json out of java object {}", json);
		response = mockMvc.perform(post("/authentication/auth").contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn().getResponse();
		assertNotNull(response);
		log.info("End of create Authentication ----> createAuthenticationTokenTest");

	}

	@Test
	@Order(6)
	void validateAuthenticationTokenTest() throws Exception {
		log.info("Testing -token generation functionality ----> createAuthenticationTokenTest");
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("srikanth", "srikanth7977");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(authenticationRequest);
		log.info("creating json out of java object {}", json);	
		
		response = mockMvc.perform(post("/authentication/auth").contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn().getResponse();
		assertNotNull(response);
		
		String content=response.getContentAsString();
		content=content.substring(8, content.length()-2);
		System.err.println("response   "+ content.substring(8, content.length()-2));
				
		
		log.info("jwt Response {}", content);
		mockMvc.perform(post("/authentication/validate")
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, content))
			.andExpect(status().isOk());			
		log.info("End of create Authentication ----> createAuthenticationTokenTest");

	}

	@Test
	@Order(7)
	void userRegistrationTest() throws Exception {

		log.info("New User Registration ---> userRegistrationTest");
		User user = new User();
		user.setUserName("sharan");
		user.setPassword("sharan123");
		user.setAccountNonExpired(true); 
		user.setAccountNonLocked(true);
		user.setEnabled(true); 
		user.setCredentialsNonExpired(true);
		user.setRoles("ROLE_USER");
		 
		log.info("User Registration, created User {}", user);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user);
		log.info("creating json out of java object {}", json);
		mockMvc.perform(post("/authentication/add-user/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());
		log.info("End of New User Registration ----> UserRegistrationTest");

	}
	
	@Test
	@Order(8)
	void userRegistrationWithExceptionTest() throws Exception {

		log.info("New User Registration ---> userRegistrationTest");
		User user = new User();
		user.setUserName("sharan");
		user.setPassword("sharan123");
		user.setAccountNonExpired(true); 
		user.setAccountNonLocked(true);
		user.setEnabled(true); 
		user.setCredentialsNonExpired(true);
		user.setRoles("ROLE_USER");
		 
		log.info("User Registration, created User {}", user);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user);
		log.info("creating json out of java object {}", json);
		mockMvc.perform(post("/authentication/add-user/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isConflict());
		//result -> assertTrue(result.getResolvedException() instanceof UserExistsWithTheGivenCredentialsException));
		userRepository.delete(user);
		log.info("End of New User Registration ----> UserRegistrationTest");

	}
	*/

}
