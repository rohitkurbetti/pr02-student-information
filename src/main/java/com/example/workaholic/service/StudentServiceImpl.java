package com.example.workaholic.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.workaholic.controller.StudentDetailsRequest;
import com.example.workaholic.entity.AssignmentDetails;
import com.example.workaholic.entity.AssignmentDetailsDom;
import com.example.workaholic.entity.SomeMapper;
import com.example.workaholic.entity.StudAssignmentDtl;
import com.example.workaholic.entity.StudentDetails;
import com.example.workaholic.entity.StudentSignUp;
import com.example.workaholic.repo.AssignmentDetailsRepository;
import com.example.workaholic.repo.StudentDetailsRepository;
import com.example.workaholic.repo.StudentSignupRepo;
import com.example.workaholic.repo.UserRepository;

@Service
public class StudentServiceImpl {

	@Autowired
	private StudentSignupRepo studentSignupRepo;
	
	@Autowired
	private StudentDetailsRepository studentDetailsRepository;
	
	@Autowired
	private AssignmentDetailsRepository assignmentDetailsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	public StudentSignUp save(StudentSignUp student) {
		return studentSignupRepo.save(student);
	}

	public StudentSignUp authenticateUser(String emailPhone, String password) {
		StudentSignUp studentSignUp = studentSignupRepo.getEntryStudent(emailPhone);
		
		if(studentSignUp != null) {
			if(studentSignUp.getPassword().equals(password)) {
				return studentSignUp;
			}
		}
		return new StudentSignUp();
	}

	public StudentDetails saveStudentDetails(StudentDetails studentDetails) {
		return studentDetailsRepository.save(studentDetails);
	}

	public StudentDetails getStudentDetailsByRollNo(String rollno) {
		return studentDetailsRepository.findByRollNo(Integer.valueOf(rollno));
	}

	public Integer updateAssignment(StudentDetailsRequest studentDetailsRequest) {
		
		List<String> queListStud = studentDetailsRequest.getQueList();
		if(queListStud != null && !queListStud.isEmpty()) {
			String questListStr = queListStud.stream().map(Object::toString).collect(Collectors.joining(", "));
			studentDetailsRepository.updateAssignmentByBranchSemester(studentDetailsRequest.getAssignmentName(), questListStr, studentDetailsRequest.getBranch(), studentDetailsRequest.getSemester());
		}
		List<Integer> rollno = extractRollNoListByBranchSemester(studentDetailsRequest);
		int counter=0;
		for (Integer integer : rollno) {
			AssignmentDetails assignmentDetails = new AssignmentDetails();
			assignmentDetails.setBranch(studentDetailsRequest.getBranch());
			assignmentDetails.setSemester(studentDetailsRequest.getSemester());
			assignmentDetails.setRollno(integer);
			assignmentDetails.setCode(studentDetailsRequest.getCode());
			assignmentDetails.setAssignmentStatus("NOTSUBMITTED");
			List<String> queList = studentDetailsRequest.getQueList();
			if(queList != null && !queList.isEmpty()) {
				String qListStr = queList.stream().map(Object::toString).collect(Collectors.joining(", "));				
				assignmentDetails.setAssignment(qListStr);
			}
			assignmentDetailsRepository.save(assignmentDetails);
			counter++;
		}
		return counter;
	}

	private List<Integer> extractRollNoListByBranchSemester(StudentDetailsRequest studentDetailsRequest) {
		String branch = studentDetailsRequest.getBranch();
		String semester = studentDetailsRequest.getSemester();
		return studentDetailsRepository.extractRollNoListByBranchSemester(branch, semester);
	}

	public List<StudentDetails> getAssignments() {
		return studentDetailsRepository.getAssignments();
	}

	public Integer updateMarksJson(StudentDetailsRequest studentDetailsRequest) {
		return studentDetailsRepository.updateMarksJsonByRollno(studentDetailsRequest.getRollno(),studentDetailsRequest.getMarksJson());
	}

	public Integer uploadNotes(MultipartFile file, String fileext, String semTxt) throws IOException {
		String fileExt = fileext.split("/")[1];
		byte[] fileData = file.getBytes();
		return studentDetailsRepository.uploadNotes(fileData, fileExt, semTxt);
	}

	public byte[] getFile1() {
		return studentDetailsRepository.getFile1();
	}

	public List<StudentDetails> getStudentSubmittedAssignmentsByAssignmentName(String assignmentName) {
		return studentDetailsRepository.getByAsssignmentName(assignmentName);
	}

	public AssignmentDetails getStudSbmtdAssignmentFile(Integer fileId, Integer assgmntCode) {
		return studentDetailsRepository.getStudSbmtdAssignmentFile(fileId,assgmntCode);
	}

	
	public List<AssignmentDetailsDom> getAssignmentsDetlsByAssgnName(String assignmentName) {
		return studentDetailsRepository.getByAsssignmentDetailsByAssgnName(assignmentName);
	}

	public List<StudAssignmentDtl> updateAssignmentsToNewlyAddedStudents() {
		
		List<StudAssignmentDtl> studAssgnDtl = studentDetailsRepository.updateAssignmentsToNewlyAddedStudents();
		
		
		studAssgnDtl.forEach(e -> {
			String barnch = e.getBranch();
			String sem = e.getSemester();
			String assgnment= e.getAssignment();
			String assgnname = e.getAssignmentName();
			studentDetailsRepository.updateASsignToNullPlaces(barnch,sem,assgnname,assgnment);
			
			List<SomeMapper> mapper=  studentDetailsRepository.addDeltaAssignments(barnch, sem);
			mapper.forEach(element -> {
				
				List<Integer> code = assignmentDetailsRepository.getASsignmentCode(barnch, sem);
				code.forEach(i -> {					
					AssignmentDetails assignmentDetails = new AssignmentDetails();
					assignmentDetails.setCode(i);
					assignmentDetails.setAssignment(element.getAssignment());
					assignmentDetails.setAssignmentStatus("NOTSUBMITTED");
					assignmentDetails.setBranch(element.getBranch());
					assignmentDetails.setRollno(element.getRollno());
					assignmentDetails.setSemester(element.getSemester());
					assignmentDetailsRepository.save(assignmentDetails);
				});
			});
			System.out.println(mapper);
		});
		
		
		
		return studAssgnDtl;
	}

	public String getTestMarksByRollno(Integer rollNoTest) {
		return studentDetailsRepository.getTestMarksByRollno(rollNoTest);
	}

	public int assignMentorToRollNos(String rollNos, String mentorName) {
		String[] nos = rollNos.split(",");
		Integer[] nosInt = new Integer[nos.length];
		for (int i = 0; i < nos.length; i++) {
			Integer rollNoUniq = Integer.parseInt(nos[i]);
			nosInt[i] = rollNoUniq;
		}
		return studentDetailsRepository.assignMentorToRollNos(nosInt, mentorName);
	}

	public int checkIfEnrlmntExists(String enrollmentId) {
		return studentDetailsRepository.checkIfEnrlmntExists(enrollmentId);
	}

	public List<StudentDetails> getAllStudentsList() {
		List<StudentDetails> studentList = studentDetailsRepository.getAllStudents();
		
		if(studentList !=null && studentList.size()>0) {
			studentList.forEach(student -> {
				String email = userRepository.getEmailByRollNo(student.getRollno());
				student.setEmail(email);
			});
		}
		return studentList;
	}

	public String deleteStudentBySemesterRollno(String semester, Integer rollno) {
		
		
		int rowsDeletedStudentDetails = studentDetailsRepository.deleteStudentDetailsBySemesterRollno(semester, rollno);
		
		int rowsDeletedAssignmentDetails = assignmentDetailsRepository.deleteAssignmentDetailsBySemesterRollno(semester, rollno);
		
		//optional
		
		int rowsDeletedUserDetails = userRepository.deleteUserDetailsByRollno(rollno);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("studentDetiails: ");
		sb.append(rowsDeletedStudentDetails);
		sb.append(",");
		sb.append("assignment details: ");
		sb.append(rowsDeletedAssignmentDetails);
		sb.append(",");
		sb.append("user details: ");
		sb.append(rowsDeletedUserDetails);
		
		return sb.toString();
	}

	public int checkIfRollNoExists(String rollNo) {
		return studentDetailsRepository.checkIfRollNoExists(Integer.parseInt(rollNo));
	}
	
	
	
}
