package com.example.workaholic.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.workaholic.entity.MiniProjectDetails;
import com.example.workaholic.repo.MiniProjectDetailsRepository;

@Service
public class MiniProjectDetailsServiceImpl {

	@Autowired
	private MiniProjectDetailsRepository miniProjectDetailsRepository;
	
	public List<MiniProjectDetails> getMiniProjectDetails(Integer rollno) {
		return miniProjectDetailsRepository.getMiniPrjDetailsByRollNo(rollno);
	}

	
	public Integer uploadPrjFile(MultipartFile file, String rollno,String subject) throws IOException {
		byte[] fileData = file.getBytes();
		return miniProjectDetailsRepository.uploadPrjFile(fileData, Integer.parseInt(rollno), subject);
	}


	public Object[] getIsProjectLeader(Integer rollno) {
		Object[] objArr = miniProjectDetailsRepository.getIsProjectLeader(rollno);
		if(objArr.length>0) {			
			if(objArr[0]==null) {
				objArr[0] = false;
			}
		}
		return objArr;
	}


	public List<Long> getPrjGrpIds(Integer rollno) {
		return miniProjectDetailsRepository.getPrjGrpIds(rollno);
	}
	
	
	
	
}
