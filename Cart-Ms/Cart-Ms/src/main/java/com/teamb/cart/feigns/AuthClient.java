package com.teamb.cart.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name="authorization-service")
public interface AuthClient {
	
	@PostMapping("/authentication/validate")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token);

}
