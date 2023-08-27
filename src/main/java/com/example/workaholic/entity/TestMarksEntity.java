package com.example.workaholic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_marks")
public class TestMarksEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "test_marks_id")
	private Long testMarksId;

	@Column(name = "rollno")
	private Integer rollno;
	
	@Column(name = "subject")
	private String subject;

	@Column(name = "test")
	private String test;
	
	@Column(name = "marks")
	private Integer marks;

	public Long getTestMarksId() {
		return testMarksId;
	}

	public void setTestMarksId(Long testMarksId) {
		this.testMarksId = testMarksId;
	}

	public Integer getRollno() {
		return rollno;
	}

	public void setRollno(Integer rollno) {
		this.rollno = rollno;
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
	
	
	
}
