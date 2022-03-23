package com.lib.authorization.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lib.authorization.util.JwtUtil;



@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("Getting Authorization key ---> JwtRequestFilter");
		String authorizationHeader = request.getHeader("Authorization");

		String userName = null;
		String jwt = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			jwt = authorizationHeader.substring(7);
			userName = jwtUtil.extractUsername(jwt);
			log.info("Getting UserName from Jwt Token ---> JwtRequestFilter");
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			log.info("Checking SecurityContextHolder is empty  ---> JwtRequestFilter");
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			log.info("Getting User from db with the Given UserName ---> JwtRequestFilter");
			boolean bool = jwtUtil.validateToken(jwt, userDetails);
			log.info("Provided Jwt Token is Valid  !!!!");
			if (bool) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				log.info("UsernamePasswordAuthenticationToken validation...");
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}

		filterChain.doFilter(request, response);
		log.info("handling the request and resonse to the next corresponding filters for validations !!!");

	}

}
