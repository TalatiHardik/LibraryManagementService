package com.lib.orders.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "wallet-ms")
public interface WalletClient {
	
	@PutMapping("/wallet/deductWallet/{username}/{orderid}/{bookName}/{fine}")
	public void deductWallet(@RequestHeader("Authorization") String token , @PathVariable String username ,@PathVariable int orderid ,@PathVariable String bookName,@PathVariable double fine);
	
}
