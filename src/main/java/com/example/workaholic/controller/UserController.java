package com.example.workaholic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workaholic.entity.LoginResponse;
import com.example.workaholic.entity.UserEntity;
import com.example.workaholic.service.UserServiceImpl;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500", "https://pr02-student-information-production.up.railway.app"})
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@GetMapping("/authenticate_user")
	public LoginResponse authenticateUser(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		LoginResponse studentSignUp = userServiceImpl.authenticateUser(email,password);
		return studentSignUp;
	}
	
	@PostMapping("/createUser")
	public UserEntity saveStudentDetails(@RequestBody UserEntity userEntity) {
		return userServiceImpl.createUser(userEntity);
	}
	
	
	@PostMapping("/updateRollnoToUser")
	public Integer updateRollnoToUser(@RequestBody UserEntity userEntity) {
		return userServiceImpl.updateRollnoToUser(userEntity);
	}
	
	@GetMapping("/getUserDetailsByEmail/{email}")
	public UserEntity updateRollnoToUser(@PathVariable("email") String email) {
		return userServiceImpl.getUserDetailsByEmail(email);
	}
	
}
