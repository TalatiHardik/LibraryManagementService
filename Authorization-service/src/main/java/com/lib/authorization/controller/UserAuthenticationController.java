package com.lib.authorization.controller;


import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lib.authorization.clients.WalletClient;
import com.lib.authorization.model.AuthenticationRequest;
import com.lib.authorization.model.AuthenticationResponse;
import com.lib.authorization.model.User;
import com.lib.authorization.service.UserDetailsServices;
import com.lib.authorization.util.JwtUtil;

import springfox.documentation.annotations.ApiIgnore;



@RestController
@RequestMapping("/authentication")
public class UserAuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(UserAuthenticationController.class);

	@Autowired
	private UserDetailsServices myUserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private WalletClient walletClient;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/add-user")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@JsonIgnoreProperties(ignoreUnknown = true)
	//@ModelAttribute("user")
	public ResponseEntity<Object> addUser(@RequestParam("user") String user1,@RequestParam("imageFile") MultipartFile  file) throws IOException {
		log.info("User details while registering{} --->UserAuthenticationController", user1);
		System.err.println("User Object");
		System.err.println(user1);
		User user = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(user1, User.class);
		user.setRoles("ROLE_USER");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		walletClient.createWallet(user.getFname());

		log.info("user Details Updated --->  UserAuthenticationController");
		return new ResponseEntity<>(myUserDetailsService.addUser(user,file), HttpStatus.CREATED);
	}

	@PostMapping("/edit-user")
	@ResponseBody
	@JsonIgnoreProperties(ignoreUnknown = true)
	public ResponseEntity<Object> editUser(@ApiIgnore @RequestHeader("Authorization") String token, @RequestParam("user") String user1,@RequestParam("imageFile") Optional<MultipartFile>  file) throws IOException  {
		if(validateToken(token.substring(7)).getStatusCodeValue()==200) {
			User user = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(user1, User.class);
			System.err.println("User Object");
			System.err.println(user);
			return new ResponseEntity<>(myUserDetailsService.editUser(user,file), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@PostMapping("/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

		log.info("Creating Jwt Token for Authentication --->  UserAuthenticationController");
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUserName(), authenticationRequest.getPassword());
		authenticationManager.authenticate(token);
		log.info("User Authentication Completed --->  UserAuthenticationController");
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		String jwt = jwtUtil.generateToken(userDetails);
		log.info("Jwt Token generated for authentication --->  UserAuthenticationController");
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}

	@PostMapping("/validate")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<AuthenticationResponse> validateToken(@RequestHeader("Authorization") String token) {
		log.info("===========>>token=================>>>" + token);
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
		log.info(" getting User Details from context  to check the validity of token ");
		jwtUtil.validateToken(token, userDetails);
		System.out.println("Expire JWT token");
		log.info("Jwt Token validation successfull !!!");
		return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse() ,HttpStatus.OK);
	}

	@PostMapping("/getName")
	public ResponseEntity<?> extractId(@ApiIgnore @RequestHeader("Authorization") String token){

		log.info("----------------------------Inside extractId token----------------------------");

		token = token.substring(7);
		UserDetails userDetails=myUserDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
		boolean result=jwtUtil.validateToken(token,userDetails);
		String name = jwtUtil.extractempid(token);
		User us = new User();
		us.setFname(name);
		if(result){
			return new ResponseEntity<>(us, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
		}

	}



	@PostMapping("/reset-pwd")
	public ResponseEntity<Object> resetPwd(@RequestBody User user) {
		user = myUserDetailsService.resetPwd(user);
		return (user!=null) ? new ResponseEntity<>(user, HttpStatus.ACCEPTED) : new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/get-user/{user}")
	public ResponseEntity<Object> getUser(@ApiIgnore @RequestHeader("Authorization") String token, @PathVariable String user) {
		if(validateToken(token.substring(7)).getStatusCodeValue()==200) {
			return new ResponseEntity<>(myUserDetailsService.getUser(user), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	/*
	 @PostMapping("/edit-user")
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 public ResponseEntity<Object> editUser(@RequestHeader("Authorization") String token, @RequestBody User user) {
		 if(validateToken(token.substring(7)).getStatusCodeValue()==200) {
			 return new ResponseEntity<>(myUserDetailsService.editUser(user), HttpStatus.ACCEPTED);
		 }
		 return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	 }*/

}
