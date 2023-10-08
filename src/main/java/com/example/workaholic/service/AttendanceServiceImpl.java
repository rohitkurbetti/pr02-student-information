package com.example.workaholic.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workaholic.entity.AttendanceEntity;
import com.example.workaholic.repo.AttendanceRepository;

@Service
public class AttendanceServiceImpl {

	@Autowired
	private AttendanceRepository attendanceRepository;

	public AttendanceEntity saveAttendance(AttendanceEntity attendanceEntity) {
		return attendanceRepository.save(attendanceEntity);
	}

	public List<AttendanceEntity> getAttendanceList() {
		return attendanceRepository.findAll();
	}

	public int delAttendanceById(String attendanceId) {
		return attendanceRepository.delAttendanceById(Integer.parseInt(attendanceId));
	}

	public Boolean getAttendanceAvg(Integer rollno) {
		AtomicBoolean ab = new AtomicBoolean(false);
		List<Object[]> subjectAvgList = attendanceRepository.getAttendanceAvg(rollno);
		
		subjectAvgList.forEach(sub -> {
			Double avg = (Double) sub[1];
			
			if(avg < 75.0d) {
				ab.set(true);
				return;
			}
		});
		return ab.get();
	}
	
}
