package com.example.workaholic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workaholic.entity.AssignmentDetailsDom;
import com.example.workaholic.entity.SubmtdAssignmentsDom;
import com.example.workaholic.service.AssignmentDetailsServiceImpl;
import com.example.workaholic.service.NotesServiceImpl;
import com.example.workaholic.service.StudentServiceImpl;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500", "https://pr02-student-information-production.up.railway.app"})
public class AssignmentController {

	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@Autowired
	private AssignmentDetailsServiceImpl assignmentDetailsServiceImpl;
	
	@Autowired
	private NotesServiceImpl notesServiceImpl;
	
	
	
	@GetMapping("/getSubmittedAssignmentsByRollNo/{rollNo}")
	public List<SubmtdAssignmentsDom> getAssignmentsDetlsByAssgnName(@PathVariable("rollNo") String rollNo) {
		List<SubmtdAssignmentsDom> studentDetails = assignmentDetailsServiceImpl.getSubmittedAssignmentsByRollNo(rollNo);
		return studentDetails;
	}
	
	
}
