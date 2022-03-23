package com.lib.authorization.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lib.authorization.filter.JwtRequestFilter;



@Configuration
@EnableWebSecurity
public class AuthenticationConfiguration extends WebSecurityConfigurerAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(AuthenticationConfiguration.class);
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	public AuthenticationProvider authProvider() {

		log.info("Inside AuthenticationProvider Bean ---> AuthenticationConfiguration");
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(encodePassword());
		log.info("creating AuthenticationProvider Bean ---> AuthenticationConfiguration");
		return daoAuthenticationProvider;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("Building Authentication Manager --->  AuthenticationConfiguration");
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public BCryptPasswordEncoder encodePassword() {
		log.info("creating BCryptPasswordEncoder Bean ---> AuthenticationConfiguration");
		return new BCryptPasswordEncoder(15);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
					//.authorizeRequests() .antMatchers("/authentication/auth").permitAll() .and()
				
				  /*.antMatchers("/authentication/validate").permitAll()	*/			  
				  //.anyRequest().authenticated() .and()
				  // .formLogin().loginPage("/authentication/login/").permitAll() 
				 
				  /*logout()
				  .invalidateHttpSession(true).clearAuthentication(true)
				  .logoutRequestMatcher(new AntPathRequestMatcher("/authentication/logout"))
				  .logoutSuccessUrl("/authentication/").permitAll()*/ //.and()
				 
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);		
		http
			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		log.info("creating AuthenticationManager Bean ---> AuthenticationConfiguration");
		return super.authenticationManagerBean();
	}

}
