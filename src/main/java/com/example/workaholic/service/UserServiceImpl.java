package com.example.workaholic.service;

import java.util.Base64;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.workaholic.entity.LoginResponse;
import com.example.workaholic.entity.UserEntity;
import com.example.workaholic.repo.UserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepository;

	public UserEntity createUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	public LoginResponse authenticateUser(String email, String password) {
		LoginResponse loginResponse = new LoginResponse();
		
		boolean isAuthenticated = false;
		password = decodeFromBase64(password);
		UserEntity userEntity = userRepository.authenticateUser(email,password);
		if(userEntity != null) {
			String emailStr = userEntity.getEmail();
			String passStr = userEntity.getPassword();
			if(email.equals(emailStr) && password.equals(passStr)) {
				isAuthenticated =true;
				loginResponse.setIsAuthenticated(isAuthenticated);
				loginResponse.setEmail(emailStr);
				loginResponse.setPassword(passStr);
				loginResponse.setRollno(userEntity.getUserRollno());
				loginResponse.setFullname(userEntity.getFullname());
			} else {				
				loginResponse.setIsAuthenticated(false);
			}
		}
		return loginResponse;
	}
	
	public static String decodeFromBase64(String encodedString) {
        // Convert the Base64-encoded string to bytes
        byte[] encodedBytes = encodedString.getBytes();
        
        // Decode the bytes from Base64
        byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        
        // Convert the decoded bytes back to a string
        String decodedString = new String(decodedBytes);
        
        return decodedString;
    }
	
	public static String encodeToBase64(String originalString) {
        // Convert the string to bytes
        byte[] originalBytes = originalString.getBytes();
        
        // Encode the bytes to Base64
        byte[] encodedBytes = Base64.getEncoder().encode(originalBytes);
        
        // Convert the encoded bytes back to a string
        String encodedString = new String(encodedBytes);
        
        return encodedString;
    }

	public Integer updateRollnoToUser(UserEntity userEntity) {
		String email = userEntity.getEmail();
		Integer userRollno = userEntity.getUserRollno();
		return userRepository.updateRollnoToUser(email,userRollno);
	}

	
	
	
}
