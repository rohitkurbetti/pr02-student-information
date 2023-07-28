package com.example.workaholic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudAssignmentDtl {

	private String assignment;
	private String assignmentName;
	private String branch;
	private String semester;
	
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
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
	public StudAssignmentDtl() {
		super();
	}
	public StudAssignmentDtl(String assignment, String assignmentName, String branch, String semester) {
		super();
		this.assignment = assignment;
		this.assignmentName = assignmentName;
		this.branch = branch;
		this.semester = semester;
	}
	
	
	
}
