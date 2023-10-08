package com.example.workaholic.repo;

import java.util.List;

import com.example.workaholic.entity.AttendanceEntity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class CustomClz {

	private Integer rollno;
	private String fullname;
	
	private List<AttendanceEntity> attendanceEntity;

	public Integer getRollno() {
		return rollno;
	}

	public void setRollno(Integer rollno) {
		this.rollno = rollno;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public List<AttendanceEntity> getAttendanceEntity() {
		return attendanceEntity;
	}

	public void setAttendanceEntity(List<AttendanceEntity> attendanceEntity) {
		this.attendanceEntity = attendanceEntity;
	}

	public CustomClz(Integer rollno, String fullname) {
		super();
		this.rollno = rollno;
		this.fullname = fullname;
	}

	public CustomClz() {
	}

	
	
	
}
