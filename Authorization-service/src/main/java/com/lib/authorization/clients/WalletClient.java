package com.lib.authorization.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "wallet-ms")
public interface WalletClient {
	
	@PostMapping("/wallet/{username}")
	public void createWallet(@PathVariable String username);
	
}
