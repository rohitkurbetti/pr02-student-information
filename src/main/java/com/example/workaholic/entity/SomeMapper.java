package com.example.workaholic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SomeMapper {
	private String assignment;
	private String branch;
	private String semester;
	private int rollno;
	public SomeMapper(String assignment, String branch, String semester, int rollno) {
		super();
		this.assignment = assignment;
		this.branch = branch;
		this.semester = semester;
		this.rollno = rollno;
	}
	public SomeMapper() {
		super();
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	
	

}
