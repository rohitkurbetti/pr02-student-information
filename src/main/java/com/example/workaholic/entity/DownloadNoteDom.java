package com.example.workaholic.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class DownloadNoteDom {

	private String filename;
    private byte[] fileNotes;
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
	public DownloadNoteDom(String filename, byte[] fileNotes) {
		super();
		this.filename = filename;
		this.fileNotes = fileNotes;
	}
	public DownloadNoteDom() {
		super();
	}
    
    
	
}
