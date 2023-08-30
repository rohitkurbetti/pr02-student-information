package com.example.workaholic.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.workaholic.entity.DownloadNoteDom;
import com.example.workaholic.entity.UploadNotesEntity;
import com.example.workaholic.repo.NotesRepository;

@Service
public class NotesServiceImpl {

	@Autowired
	private NotesRepository notesRepository;
	
	public UploadNotesEntity uploadNotes(MultipartFile file, String fileext, String semTxt, String subject, String fileName) throws IOException {
		String fileExt = fileext.split("/")[1];
		byte[] fileData = file.getBytes();
		
		UploadNotesEntity uploadNotesEntity = new UploadNotesEntity();
		uploadNotesEntity.setFileExt(fileExt);
		uploadNotesEntity.setFilename(fileName);
		uploadNotesEntity.setFileNotes(fileData);
		uploadNotesEntity.setBranch("co");
		uploadNotesEntity.setSemester(semTxt);
		uploadNotesEntity.setSubject(subject);
		return notesRepository.save(uploadNotesEntity);
	}

	public Page<UploadNotesEntity> getAllNotes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
		return notesRepository.findAll(pageable);
	}

	public DownloadNoteDom getNote(Integer fileId) {
		return notesRepository.getNote(fileId);
	}

	public List<UploadNotesEntity> getNotesBySemester(String semester) {
		return notesRepository.getNotesBySemester(semester);
	}

	public int deleteNotesById(Integer deleteNoteId) {
		return notesRepository.deleteNotesById(deleteNoteId);
	}
	
}
