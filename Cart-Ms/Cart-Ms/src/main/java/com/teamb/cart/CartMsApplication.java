package com.teamb.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableFeignClients
public class CartMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartMsApplication.class, args);
	}

	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/add-to-cart/*/*")
						.or(PathSelectors.ant("/get-cart/*"))
						.or(PathSelectors.ant("/get-cart-size/*"))
						.or(PathSelectors.ant("/find-by-name/*"))
						.or(PathSelectors.ant("/remove-book/*/*"))
						.or(PathSelectors.ant("/check-out/*")))
				.apis(RequestHandlerSelectors.basePackage("com.teamb.cart"))
				.build()
				.apiInfo(null);
	}
	
}
