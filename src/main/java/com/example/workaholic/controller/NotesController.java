package com.example.workaholic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workaholic.entity.DownloadNoteDom;
import com.example.workaholic.entity.UploadNotesEntity;
import com.example.workaholic.service.NotesServiceImpl;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500", "https://pr02-student-information-production.up.railway.app"})
public class NotesController {

	@Autowired
	private NotesServiceImpl notesServiceImpl;
	
	
	@GetMapping("/getAllNotes")
	public Page<UploadNotesEntity> getAllNotes(@RequestParam("page")int page, @RequestParam("size")int size) {
		Page<UploadNotesEntity> notesList = notesServiceImpl.getAllNotes(page,size);
		notesList.forEach(i -> i.setFileNotes(null));
		return notesList;
	}
	
	@GetMapping("/getNote/{fileId}")
	public DownloadNoteDom getNote(@PathVariable("fileId") Integer fileId) {
		DownloadNoteDom studentDetails = notesServiceImpl.getNote(fileId);
		return studentDetails;
	}
	
	@GetMapping("/getNotesBySemester/{semester}")
	public List<UploadNotesEntity> getNotesBySemester(@PathVariable("semester") String semester) {
		List<UploadNotesEntity> notesList = notesServiceImpl.getNotesBySemester(semester);
		notesList.forEach(i -> i.setFileNotes(null));
		return notesList;
	}
	
	@GetMapping("/deleteNotesById")
	public int deleteNotesById(@RequestParam("deleteNoteId") Integer deleteNoteId) {
		return notesServiceImpl.deleteNotesById(deleteNoteId);
	}
	
	
}
