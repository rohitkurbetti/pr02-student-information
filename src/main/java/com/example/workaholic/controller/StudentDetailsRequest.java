package com.example.workaholic.controller;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class StudentDetailsRequest {
		
	private String assignmentName;
	private String branch;
	private String semester;
	private List<String> queList;
	private Integer rollno;
	private String marksJson;
	private MultipartFile file;
	private Integer code;
	private String title;
	private String content;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
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
	public List<String> getQueList() {
		return queList;
	}
	public void setQueList(List<String> queList) {
		this.queList = queList;
	}
	public Integer getRollno() {
		return rollno;
	}
	public void setRollno(Integer rollno) {
		this.rollno = rollno;
	}
	public String getMarksJson() {
		return marksJson;
	}
	public void setMarksJson(String marksJson) {
		this.marksJson = marksJson;
	}
	
	@Override
	public String toString() {
		return "StudentDetailsRequest [assignmentName=" + assignmentName + ", branch=" + branch + ", semester="
				+ semester + ", queList=" + queList + ", rollno=" + rollno + ", marksJson=" + marksJson + ", file="
				+ file + ", code=" + code + "]";
	}
	
	
	
	
	
	
	
	

}
