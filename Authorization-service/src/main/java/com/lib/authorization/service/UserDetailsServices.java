package com.lib.authorization.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.lib.authorization.model.User;

public interface UserDetailsServices extends UserDetailsService {

	User addUser(User user, MultipartFile file) throws IOException;
	
	User resetPwd(User user);
	
	User getUser(String user);
	
	User editUser(User user, Optional<MultipartFile> file) throws IOException;


}