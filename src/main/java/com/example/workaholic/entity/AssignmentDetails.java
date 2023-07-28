package com.example.workaholic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "assignment_details")
public class AssignmentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "assignment_details_id")
	private Integer assignmentDetailsId;

	@Column(name = "rollno")
	private Integer rollno;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "semester")
	private String semester;

	@Column(name = "code")
	private Integer code;
	
	@Column(name = "assignment")
	private String assignment;
	
	@Column(name = "assignment_status")
	private String assignmentStatus;
	
	
//	@Column(name = "marks_json")
//	private String marksJson;
	
//	@Lob
//    @Column(name = "file_notes")
//    private byte[] fileNotes;
	
	@Column(name = "file_ext")
	private String fileExt;
	
	@Lob
    @Column(name = "student_submitted_assignments")
    private byte[] studentSubmittedAssignments;

	
	public AssignmentDetails(Integer code, String assignment) {
		this.code = code;
		this.assignment = assignment;
	}
	
	
	
	public AssignmentDetails() {
		super();
	}



	public Integer getAssignmentDetailsId() {
		return assignmentDetailsId;
	}

	public void setAssignmentDetailsId(Integer assignmentDetailsId) {
		this.assignmentDetailsId = assignmentDetailsId;
	}

	public Integer getRollno() {
		return rollno;
	}

	public void setRollno(Integer rollno) {
		this.rollno = rollno;
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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public String getAssignmentStatus() {
		return assignmentStatus;
	}

	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public byte[] getStudentSubmittedAssignments() {
		return studentSubmittedAssignments;
	}

	public void setStudentSubmittedAssignments(byte[] studentSubmittedAssignments) {
		this.studentSubmittedAssignments = studentSubmittedAssignments;
	}
	
	
}
