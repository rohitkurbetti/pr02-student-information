package com.example.workaholic.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class LoginResponse {

	private Boolean isAuthenticated;
	private String email;
	private String password;
	private Integer rollno;
	private String fullname;
	
	public LoginResponse() {
		super();
	}
	public LoginResponse(Boolean isAuthenticated, String email, String password) {
		super();
		this.isAuthenticated = isAuthenticated;
		this.email = email;
		this.password = password;
	}
	public Boolean getIsAuthenticated() {
		return isAuthenticated;
	}
	public void setIsAuthenticated(Boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRollno() {
		return rollno;
	}
	public void setRollno(Integer rollno) {
		this.rollno = rollno;
	}
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public LoginResponse(Boolean isAuthenticated, String email, String password, Integer rollno, String fullname) {
		super();
		this.isAuthenticated = isAuthenticated;
		this.email = email;
		this.password = password;
		this.rollno = rollno;
		this.fullname = fullname;
	}
	public LoginResponse(Boolean isAuthenticated, String email, String password, Integer rollno) {
		super();
		this.isAuthenticated = isAuthenticated;
		this.email = email;
		this.password = password;
		this.rollno = rollno;
	}
	
	
	
}
