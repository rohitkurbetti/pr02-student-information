package com.example.workaholic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "student_details")
public class StudentDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_details_id")
	private Integer studentDetailsId;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "enrollmentId")
	private Long enrollmentId;
	
	@Column(name = "rollno")
	private Integer rollno;
	
	@Column(name = "contactno")
	private String contactno;

	@Column(name = "semester")
	private String semester;

	@Column(name = "branch")
	private String branch;
	
	@Column(name = "previous_sem_result")
	private String previousSemResult;

	@Column(name = "assignment_name")
	private String assignmentName;
	
	@Column(name = "assignment")
	private String assignment;
	
	@Column(name = "marks_json")
	private String marksJson;
	
	@Lob
    @Column(name = "file_notes")
    private byte[] fileNotes;
	
	@Column(name = "file_ext")
	private String fileExt;
	
	@Lob
    @Column(name = "student_submitted_assignments")
    private byte[] studentSubmittedAssignments;
	
	@Column(name = "mentor_name")
	private String mentorName;
	
	
	
	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public StudentDetails(Integer rollno) {
		this.rollno = rollno;
	}
	
	public StudentDetails() {
		super();
	}


	public Integer getStudentDetailsId() {
		return studentDetailsId;
	}


	public void setStudentDetailsId(Integer studentDetailsId) {
		this.studentDetailsId = studentDetailsId;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public Long getEnrollmentId() {
		return enrollmentId;
	}


	public void setEnrollmentId(Long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}


	public Integer getRollno() {
		return rollno;
	}


	public void setRollno(Integer rollno) {
		this.rollno = rollno;
	}


	public String getContactno() {
		return contactno;
	}


	public void setContactno(String contactno) {
		this.contactno = contactno;
	}


	public String getSemester() {
		return semester;
	}


	public void setSemester(String semester) {
		this.semester = semester;
	}


	public String getBranch() {
		return branch;
	}


	public void setBranch(String branch) {
		this.branch = branch;
	}


	public String getPreviousSemResult() {
		return previousSemResult;
	}


	public void setPreviousSemResult(String previousSemResult) {
		this.previousSemResult = previousSemResult;
	}


	public String getAssignmentName() {
		return assignmentName;
	}


	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}


	public String getAssignment() {
		return assignment;
	}


	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}


	public String getMarksJson() {
		return marksJson;
	}


	public void setMarksJson(String marksJson) {
		this.marksJson = marksJson;
	}


	public byte[] getFileNotes() {
		return fileNotes;
	}


	public void setFileNotes(byte[] fileNotes) {
		this.fileNotes = fileNotes;
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

	
	public StudentDetails(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public StudentDetails(Integer studentDetailsId, String fullname, Long enrollmentId, Integer rollno,
			String contactno, String semester, String branch, String previousSemResult, String assignmentName,
			String assignment, String marksJson, byte[] fileNotes, String fileExt, byte[] studentSubmittedAssignments) {
		super();
		this.studentDetailsId = studentDetailsId;
		this.fullname = fullname;
		this.enrollmentId = enrollmentId;
		this.rollno = rollno;
		this.contactno = contactno;
		this.semester = semester;
		this.branch = branch;
		this.previousSemResult = previousSemResult;
		this.assignmentName = assignmentName;
		this.assignment = assignment;
		this.marksJson = marksJson;
		this.fileNotes = fileNotes;
		this.fileExt = fileExt;
		this.studentSubmittedAssignments = studentSubmittedAssignments;
	}


	public StudentDetails(String fullname, Long enrollmentId, Integer rollno, String contactno, String semester,
			String branch, String previousSemResult, String assignmentName, String assignment, String marksJson,
			byte[] fileNotes, String fileExt, byte[] studentSubmittedAssignments) {
		super();
		this.fullname = fullname;
		this.enrollmentId = enrollmentId;
		this.rollno = rollno;
		this.contactno = contactno;
		this.semester = semester;
		this.branch = branch;
		this.previousSemResult = previousSemResult;
		this.assignmentName = assignmentName;
		this.assignment = assignment;
		this.marksJson = marksJson;
		this.fileNotes = fileNotes;
		this.fileExt = fileExt;
		this.studentSubmittedAssignments = studentSubmittedAssignments;
	}


	
	
}
