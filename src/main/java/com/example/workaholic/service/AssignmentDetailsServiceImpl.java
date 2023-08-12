package com.example.workaholic.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.workaholic.entity.AssignmentDetails;
import com.example.workaholic.entity.AssignmentDetailsDom;
import com.example.workaholic.entity.SubmtdAssignmentsDom;
import com.example.workaholic.repo.AssignmentDetailsRepository;
import com.example.workaholic.repo.StudentDetailsRepository;

@Service
public class AssignmentDetailsServiceImpl {

	@Autowired
	private AssignmentDetailsRepository assignmentDetailsRepository;
	
	@Autowired
	private StudentDetailsRepository studentDetailsRepository;
	
	public List<AssignmentDetailsDom> getAssignmentsByRollno(String rollno) {
		return assignmentDetailsRepository.getAssignmentsByRollno(Integer.parseInt(rollno));
	}

	public Integer updateStudentAssignments(MultipartFile file, String fileext, String rollno, String code) throws IOException {
		String fileExt = fileext.split("/")[1];
		byte[] fileData = file.getBytes();
		return assignmentDetailsRepository.updateStudentAssignments(fileData, fileExt, Integer.parseInt(rollno), Integer.parseInt(code));
	}

	public List<AssignmentDetailsDom> getAssignmentsDetlsByAssgnCode(Integer assignmentCode) {
		return assignmentDetailsRepository.getAssignmentsDetlsByAssgnCode(assignmentCode);
	}

	public List<SubmtdAssignmentsDom> getSubmittedAssignmentsByRollNo(String rollno) {
		return assignmentDetailsRepository.getSubmittedAssignmentsByRollNo(Integer.parseInt(rollno));
	}
	
}
