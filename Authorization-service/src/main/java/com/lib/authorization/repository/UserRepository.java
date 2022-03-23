package com.lib.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lib.authorization.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	
	User findByEmail(String userName);

	User findByFname(String username);

}



