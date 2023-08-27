package com.example.workaholic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workaholic.entity.StudentSignUp;
import com.example.workaholic.entity.TestMarksEntity;
import com.example.workaholic.entity.TestMarksRequestDto;
import com.example.workaholic.service.TestMarksServiceImpl;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500", "https://pr02-student-information-production.up.railway.app"})
public class TestMarksController {

	
	@Autowired
	private TestMarksServiceImpl testMarksServiceImpl;
	
	
	@PostMapping("/saveStudentMarks")
	public int testMarksEntity(@RequestBody TestMarksRequestDto testMarksEntity) {
		return testMarksServiceImpl.saveTestMarks(testMarksEntity);
	}
	
	
	@GetMapping("/getStudentMarksByRollNo/{rollno}")
	public List<TestMarksEntity> getStudentMarksByRollNo(@PathVariable("rollno") String rollno) {
		List<TestMarksEntity> testMarksList = testMarksServiceImpl.getStudentMarksByRollNo(rollno);
		return testMarksList;
	}
	
	@GetMapping("/deleteStudMarksById")
	public int deleteStudMarksById(@RequestParam("testRemoveId") String testRemoveId) {
		int studentSignUp = testMarksServiceImpl.deleteStudMarksById(testRemoveId);
		return studentSignUp;
	}
	
	
}
