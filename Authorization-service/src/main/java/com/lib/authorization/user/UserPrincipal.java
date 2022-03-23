package com.lib.authorization.user;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lib.authorization.model.User;



public class UserPrincipal implements UserDetails {

	private static final Logger log = LoggerFactory.getLogger(UserPrincipal.class);

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public UserPrincipal(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		log.info("Getting the authorities of a User  --->  UserPrincipal");
		return Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority:: new).collect(Collectors.toList());
				
	}

	@Override
	public String getPassword() {
		log.info("getting password of User  --->  UserPrincipal");
		return user.getPwd();
	}

	@Override
	public boolean isAccountNonExpired() {
		log.info("checking whether User Account is Expired or not --->  UserPrincipal");
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		log.info("checking whether User Account is Locked or not  --->  UserPrincipal");
		return user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		log.info("checking whether User Credentials is Expired or not  --->  UserPrincipal");
		return user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		log.info("checking whether User is Enabled  --->  UserPrincipal");
		return user.isEnabled();
	}

	@Override
	public String getUsername() {
		log.info("getting UserName of User  --->  UserPrincipal");
		
		
		
		
		return user.getFname();
	}

	

}
