package com.example.workaholic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attendance_details")
public class AttendanceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attendance_details_id")
	private Integer attendanceDetailsId;

	@Column(name = "rollno")
	private Integer rollno;
	
	@Column(name = "month")
	private Integer month;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "lectures_attended")
	private Integer lecturesAttended;
	
	@Column(name = "total_lectures")
	private Integer totalLectures;
	
	@Column(name = "attendance_percent")
	private Double attendancePercent;

	
	public Integer getAttendanceDetailsId() {
		return attendanceDetailsId;
	}

	public void setAttendanceDetailsId(Integer attendanceDetailsId) {
		this.attendanceDetailsId = attendanceDetailsId;
	}

	public Integer getRollno() {
		return rollno;
	}

	public void setRollno(Integer rollno) {
		this.rollno = rollno;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getLecturesAttended() {
		return lecturesAttended;
	}

	public void setLecturesAttended(Integer lecturesAttended) {
		this.lecturesAttended = lecturesAttended;
	}

	public Integer getTotalLectures() {
		return totalLectures;
	}

	public void setTotalLectures(Integer totalLectures) {
		this.totalLectures = totalLectures;
	}

	public Double getAttendancePercent() {
		return attendancePercent;
	}

	public void setAttendancePercent(Double attendancePercent) {
		this.attendancePercent = attendancePercent;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
}
