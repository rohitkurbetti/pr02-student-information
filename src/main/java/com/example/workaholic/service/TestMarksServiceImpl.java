package com.example.workaholic.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workaholic.entity.AssignmentDetailsDom;
import com.example.workaholic.entity.StudentSignUp;
import com.example.workaholic.entity.TestMarksDto;
import com.example.workaholic.entity.TestMarksEntity;
import com.example.workaholic.entity.TestMarksRequestDto;
import com.example.workaholic.repo.TestMarksRepository;

@Service
public class TestMarksServiceImpl {

	@Autowired
	private TestMarksRepository testMarksRepository;

	public int saveTestMarks(TestMarksRequestDto testMarksRequestDto) {
		AtomicInteger counter= new AtomicInteger(0);
		if(testMarksRequestDto != null && testMarksRequestDto.getMarksJson().size()>0) {
			List<TestMarksDto> testMarksDto = testMarksRequestDto.getMarksJson();
			testMarksDto.forEach(marks -> {
				TestMarksEntity testMarksEntity = new TestMarksEntity();
				testMarksEntity.setTestMarksId(marks.getTestMarksId());
				testMarksEntity.setRollno(marks.getRollNoTest());
				testMarksEntity.setSubject(marks.getSubject());
				testMarksEntity.setTest(marks.getTest());
				testMarksEntity.setMarks(marks.getMarks());
				testMarksRepository.save(testMarksEntity);
				counter.addAndGet(1);
			});
		}
		
		return counter.get();
	}

	public List<TestMarksEntity> getStudentMarksByRollNo(String rollno) {
		return testMarksRepository.getStudentMarksByRollNo(Integer.parseInt(rollno));
	}

	public int deleteStudMarksById(String testRemoveId) {
		return testMarksRepository.deleteStudMarksById(Long.parseLong(testRemoveId));
	}
	
	
	
}
