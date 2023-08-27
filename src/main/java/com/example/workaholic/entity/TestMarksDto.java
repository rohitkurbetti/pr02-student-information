package com.example.workaholic.entity;

public class TestMarksDto {

	private Long testMarksId;
	private Integer rollNoTest;
	private String subject;
	private String test;
	private Integer marks;
	
	public Long getTestMarksId() {
		return testMarksId;
	}
	public void setTestMarksId(Long testMarksId) {
		this.testMarksId = testMarksId;
	}
	public Integer getRollNoTest() {
		return rollNoTest;
	}
	public void setRollNoTest(Integer rollNoTest) {
		this.rollNoTest = rollNoTest;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public Integer getMarks() {
		return marks;
	}
	public void setMarks(Integer marks) {
		this.marks = marks;
	}
	public TestMarksDto(Integer rollNoTest, String subject, String test, Integer marks) {
		super();
		this.rollNoTest = rollNoTest;
		this.subject = subject;
		this.test = test;
		this.marks = marks;
	}
	public TestMarksDto() {
		super();
	}
	
	
	
	
}
