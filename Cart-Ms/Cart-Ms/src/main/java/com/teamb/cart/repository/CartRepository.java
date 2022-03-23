package com.teamb.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamb.cart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByUserName(String userName);
	
	List<Cart> findByUserNameAndBookName(String userName, String bookName);
	
	@Transactional
	@Modifying
	@Query("delete from Cart c where c.userName = ?1")
	void delAllByUserName(String userName);

}
