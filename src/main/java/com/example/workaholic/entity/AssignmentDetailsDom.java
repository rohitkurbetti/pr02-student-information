package com.example.workaholic.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class AssignmentDetailsDom {
	

	private int rollno;
	private String fullname;
	private int code;
	private String assignmentStatus;
	private byte[] studentSubmittedAssignments;
	
	
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

	public byte[] getStudentSubmittedAssignments() {
		return studentSubmittedAssignments;
	}

	public void setStudentSubmittedAssignments(byte[] studentSubmittedAssignments) {
		this.studentSubmittedAssignments = studentSubmittedAssignments;
	}

	public AssignmentDetailsDom() {
		super();
	}

	public AssignmentDetailsDom(int rollno, String fullname, int code, String assignmentStatus,
			byte[] studentSubmittedAssignments) {
		super();
		this.rollno = rollno;
		this.fullname = fullname;
		this.code = code;
		this.assignmentStatus = assignmentStatus;
		this.studentSubmittedAssignments = studentSubmittedAssignments;
	}
	
	

	
}
