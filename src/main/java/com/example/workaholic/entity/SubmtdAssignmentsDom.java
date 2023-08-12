package com.example.workaholic.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class SubmtdAssignmentsDom {

	
	private Integer rollno;
	private String assignmentName;
	private Integer code;
	private String assignmentStatus;
	
	public Integer getRollno() {
		return rollno;
	}
	public void setRollno(Integer rollno) {
		this.rollno = rollno;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getAssignmentStatus() {
		return assignmentStatus;
	}
	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}
	
	public SubmtdAssignmentsDom() {
		super();
	}
	
	public SubmtdAssignmentsDom(Integer rollno, String assignmentName, Integer code, String assignmentStatus) {
		super();
		this.rollno = rollno;
		this.assignmentName = assignmentName;
		this.code = code;
		this.assignmentStatus = assignmentStatus;
	}
	
	
	
	
	
	
}
