package com.lib.WalletMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lib.WalletMS.models.Wallet;


public interface WalletRepo extends JpaRepository<Wallet, Integer>{
	
	Wallet findByUsername(String username);

	@Query(value = "SELECT distinct(f.order_id) FROM Wallet w JOIN Fined_order f ON w.wallet_Id=f.wallet_Id AND w.username=:username AND f.transaction='Deducted'", nativeQuery = true)
	List<Integer> getOrderIds(String username);
	
	
}
