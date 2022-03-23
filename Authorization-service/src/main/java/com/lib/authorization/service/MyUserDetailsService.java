package com.lib.authorization.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lib.authorization.exceptions.UserExistsWithTheGivenCredentialsException;
import com.lib.authorization.model.User;
import com.lib.authorization.repository.UserRepository;
import com.lib.authorization.user.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsServices {

	private static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		log.info("inside loadUserByUsername method --> MyUserDetailsService");
		User user=userRepository.findByEmail(username);	
		User user2=userRepository.findByFname(username);
		user=(user==null)?user2:user;
		if (user==null) {
			log.error("throwing UsernameNotFoundException inside loadUserByUsername method --> MyUserDetailsService");
			throw new UsernameNotFoundException("user Not found");
		}
		
		log.info("User found with the Given User Name --> MyUserDetailsService");
		//user.setImage(decompressBytes(user.getImage()));
		return new UserPrincipal(user);
	}

	@Override
	public User addUser( User user,MultipartFile file) throws IOException {
		log.info("Inside AddingUser Method ----> MyUserDetailsService");
		if(userRepository.findByEmail(user.getEmail())==null && userRepository.findByFname(user.getFname())==null) {	
			log.info("Encrypting the password of user ----> MyUserDetailsService");
			user.setPwd( new BCryptPasswordEncoder (15).encode(user.getPwd()));
			user.setImage(compressBytes(file.getBytes()));
			userRepository.save(user);	
			log.info("user registration is successful !!! ----> MyUserDetailsService");
			return user;
		}
		log.error("User Already Exists So Exception was raised !!! --->  MyUserDetailsService");
		throw new UserExistsWithTheGivenCredentialsException("User Exists please provide new Credentials !!!");
	}

	@Override
	public User editUser(User user,Optional<MultipartFile> file) throws IOException {
		// TODO Auto-generated method stub
		User x = userRepository.findByFname(user.getFname());
		x.setLname(user.getLname());
		x.setGender(user.getGender());
		x.setPhone(user.getPhone());
		x.setCity(user.getCity());
		if(file.isPresent())
			x.setImage(compressBytes(file.get().getBytes()));
		
		userRepository.save(x);
		return x;
	}

	@Override
	public User resetPwd(User user) {
		// TODO Auto-generated method stub
		User x = userRepository.findByEmail(user.getEmail());
		if(x!=null && user.getSq()==x.getSq() && user.getSqa().equals(x.getSqa())) {
			x.setPwd(new BCryptPasswordEncoder (15).encode(user.getPwd()));
			userRepository.save(x);
			return x;
		}
		return null;
	}

	@Override
	public User getUser(String user) {
		// TODO Auto-generated method stub
		User x = userRepository.findByEmail(user);
		User y = userRepository.findByFname(user);
		x = (x!=null)?x:y;
		x.setPwd(null);
		x.setSq(0);
		x.setSqa(null);
		x.setImage(decompressBytes(x.getImage()));
		return x;
	}
	
	
	// compress the image bytes before storing it in the database

		public static byte[] compressBytes(byte[] data) {
			Deflater deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
			return outputStream.toByteArray();
		}
		
		// uncompress the image bytes before returning it to the angular application

		 public static byte[] decompressBytes(byte[] data) {
		        Inflater inflater = new Inflater();
		        inflater.setInput(data);
		        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		        byte[] buffer = new byte[1024];
		        try {
		            while (!inflater.finished()) {
		                int count = inflater.inflate(buffer);
		                outputStream.write(buffer, 0, count);
		            }
		            outputStream.close();
		        } catch (IOException ioe) {
		        } catch (DataFormatException e) {
		        }
		        return outputStream.toByteArray();
		    }

	
	
	

}
