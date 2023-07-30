package com.example.workaholic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.workaholic.entity.DownloadNoteDom;
import com.example.workaholic.entity.UploadNotesEntity;

@Repository
@Transactional
public interface NotesRepository extends JpaRepository<UploadNotesEntity, Integer>{

	
	@Query("select new com.example.workaholic.entity.DownloadNoteDom(un.filename, un.fileNotes) from UploadNotesEntity un where un.noteDetailsId=:fileId ")
	DownloadNoteDom getNote(@Param("fileId") Integer fileId);

	@Query("select un from UploadNotesEntity un where un.semester=:semester ")
	List<UploadNotesEntity> getNotesBySemester(@Param("semester") String semester);

	
}
