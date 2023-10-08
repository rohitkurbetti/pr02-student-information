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

import com.example.workaholic.entity.AttendanceEntity;
import com.example.workaholic.service.AttendanceServiceImpl;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500", "https://pr02-student-information-production.up.railway.app"})
public class AttendanceController {

	@Autowired
	private AttendanceServiceImpl attendanceServiceImpl;
	
	
	@PostMapping("/saveAttendance")
	public AttendanceEntity saveAttendance(@RequestBody AttendanceEntity attendanceEntity) {
		return attendanceServiceImpl.saveAttendance(attendanceEntity);
	}
	
	@GetMapping("/getAttendanceList")
	public List<AttendanceEntity> getStudentMarksByRollNo() {
		List<AttendanceEntity> attendanceEntities = attendanceServiceImpl.getAttendanceList();
		return attendanceEntities;
	}
	
	
	@GetMapping("/delAttendanceById")
	public int delAttendanceById(@RequestParam("attendanceId") String attendanceId) {
		int studentSignUp = attendanceServiceImpl.delAttendanceById(attendanceId);
		return studentSignUp;
	}
	
	@GetMapping("/getAttendanceAvg/{rollno}")
	public Boolean getAttendanceAvg(@PathVariable("rollno") Integer rollno) {
		Boolean isAttendanceBelowThreshold = attendanceServiceImpl.getAttendanceAvg(rollno);
		return isAttendanceBelowThreshold;
	}
	
	
	
}
