package com.lib.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lib.orders.models.OrdersEntity;


@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {
	

	List<OrdersEntity> findByOrderidAndUsername(int orderid, String username);

	//return list of orders of a user 
	List<OrdersEntity> findByUsername(String username);

	List<OrdersEntity> findByOrderidAndUsernameAndCheckedBookName(int orderid, String username, String name);
	
	@Query("select distinct o.orderid from OrdersEntity o where o.username=?1")
	List<Integer> getDistinctOrderIds(String username);
	
}
