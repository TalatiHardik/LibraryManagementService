package com.lib;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableFeignClients
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class LibraryMs3Application {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMs3Application.class, args);
	}
	
//	@Bean
//	public Docket swaggerConfig() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.paths(
//						/*
//						PathSelectors.ant("/books/get-all-available-books")
//						.or(PathSelectors.ant("/books/pick-book"))
//						.or(PathSelectors.ant("/books/drop-book"))
//						.or
//						*/
//						(PathSelectors.ant("/books/bookName/*"))
//						.or(PathSelectors.ant("/books/get-all-books"))
//						.or(PathSelectors.ant("/books/decrease-quantity/*"))
//						.or(PathSelectors.ant("/books/increase-quantity/*")))
//				.apis(RequestHandlerSelectors.basePackage("com.teamb"))
//				.build()
//				.apiInfo(null);
//	}
	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 

	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
	
	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	      .securityContexts(Arrays.asList(securityContext()))
	      .securitySchemes(Arrays.asList(apiKey()))
	      .select()
	      .apis(RequestHandlerSelectors.any())
	      .paths(PathSelectors.any())
	      .build();
	}

}
