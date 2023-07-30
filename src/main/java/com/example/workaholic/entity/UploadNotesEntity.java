package com.example.workaholic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "note_details")
public class UploadNotesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "note_details_id")
	private Integer noteDetailsId;

	@Column(name = "semester")
	private String semester;

	@Column(name = "branch")
	private String branch;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "filename")
	private String filename;
	
	@Lob
    @Column(name = "file_notes")
    private byte[] fileNotes;
	
	@Column(name = "file_ext")
	private String fileExt;

	public Integer getNoteDetailsId() {
		return noteDetailsId;
	}

	public void setNoteDetailsId(Integer noteDetailsId) {
		this.noteDetailsId = noteDetailsId;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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

	public UploadNotesEntity(Integer noteDetailsId, String semester, String branch, String subject, String filename,
			byte[] fileNotes, String fileExt) {
		super();
		this.noteDetailsId = noteDetailsId;
		this.semester = semester;
		this.branch = branch;
		this.subject = subject;
		this.filename = filename;
		this.fileNotes = fileNotes;
		this.fileExt = fileExt;
	}

	public UploadNotesEntity(String semester, String branch, String subject, String filename, byte[] fileNotes,
			String fileExt) {
		super();
		this.semester = semester;
		this.branch = branch;
		this.subject = subject;
		this.filename = filename;
		this.fileNotes = fileNotes;
		this.fileExt = fileExt;
	}

	public UploadNotesEntity() {
		super();
	}
	
	

}
