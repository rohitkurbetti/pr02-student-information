package com.example.workaholic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user_details")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_details_id")
	private Integer userDetailsId;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "user_rollno")
	private Integer userRollno;
	
	@Transient
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getUserRollno() {
		return userRollno;
	}

	public void setUserRollno(Integer userRollno) {
		this.userRollno = userRollno;
	}

	public Integer getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Integer userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public UserEntity(String fullname, String email, String password) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}

	public UserEntity(Integer userDetailsId, String fullname, String email, String password) {
		super();
		this.userDetailsId = userDetailsId;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}

	public UserEntity() {
		super();
	}

	public UserEntity(Integer userDetailsId, String fullname, String email, String password, Integer userRollno) {
		super();
		this.userDetailsId = userDetailsId;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.userRollno = userRollno;
	}
	
	

}
