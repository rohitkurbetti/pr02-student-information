package com.example.workaholic.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class AssignmentDetailsDom {
	

	private int rollno;
	private String fullname;
	private String assignmentName;
	private int code;
	private String assignmentStatus;
	
	
	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getAssignmentStatus() {
		return assignmentStatus;
	}

	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	public AssignmentDetailsDom(int rollno, String fullname, String assignmentName, int code, String assignmentStatus) {
		super();
		this.rollno = rollno;
		this.fullname = fullname;
		this.assignmentName = assignmentName;
		this.code = code;
		this.assignmentStatus = assignmentStatus;
	}

	public AssignmentDetailsDom() {
		super();
	}

	public AssignmentDetailsDom(int rollno, String fullname, int code, String assignmentStatus) {
		super();
		this.rollno = rollno;
		this.fullname = fullname;
		this.code = code;
		this.assignmentStatus = assignmentStatus;
	}
	
	

	
	
	

	
}
