package com.lib.WalletMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lib.WalletMS.models.FinedOrder;

public interface FinedOrderRepo extends JpaRepository<FinedOrder, Integer> {
	
	List<FinedOrder> findByOrderIdAndBookName(int orderId, String bookName);
	
	@Query(value = "select * from fined_order f where f.order_id=:orderid", nativeQuery = true)
	List<FinedOrder> getOrderDetail(int orderid);

}
