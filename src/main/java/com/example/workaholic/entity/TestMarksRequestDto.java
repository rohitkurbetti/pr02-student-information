package com.example.workaholic.entity;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class TestMarksRequestDto {

	private List<TestMarksDto> marksJson;
	private Integer rollno;
	
	public TestMarksRequestDto(List<TestMarksDto> marksJson, Integer rollno) {
		super();
		this.marksJson = marksJson;
		this.rollno = rollno;
	}

	public TestMarksRequestDto() {
		super();
	}

	public List<TestMarksDto> getMarksJson() {
		return marksJson;
	}

	public void setMarksJson(List<TestMarksDto> marksJson) {
		this.marksJson = marksJson;
	}

	public Integer getRollno() {
		return rollno;
	}

	public void setRollno(Integer rollno) {
		this.rollno = rollno;
	}
	
	
	
	
}
