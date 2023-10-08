package com.example.workaholic.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.workaholic.entity.AssignmentDetails;
import com.example.workaholic.entity.AssignmentDetailsDom;
import com.example.workaholic.entity.MiniProjectDetails;
import com.example.workaholic.entity.MiniProjectsWrapper;
import com.example.workaholic.entity.StudAssignmentDtl;
import com.example.workaholic.entity.StudentDetails;
import com.example.workaholic.entity.StudentSignUp;
import com.example.workaholic.entity.UploadNotesEntity;
import com.example.workaholic.repo.CustomClz;
import com.example.workaholic.service.AssignmentDetailsServiceImpl;
import com.example.workaholic.service.MiniProjectDetailsServiceImpl;
import com.example.workaholic.service.NotesServiceImpl;
import com.example.workaholic.service.StudentServiceImpl;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500", "https://pr02-student-information-production.up.railway.app"})
public class StudentController {
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@Autowired
	private AssignmentDetailsServiceImpl assignmentDetailsServiceImpl;
	
	@Autowired
	private NotesServiceImpl notesServiceImpl;
	
	@Autowired
	private MiniProjectDetailsServiceImpl miniProjectDetailsServiceImpl;
	
	@PostMapping("/student_signup")
	public StudentSignUp createEmployee(@RequestBody StudentSignUp student) {
		studentServiceImpl.save(student);
		return student;
	}
	
	@GetMapping("/authenticate_user1")
	public StudentSignUp authenticateUser(@RequestParam("emailPhone") String emailPhone,
			@RequestParam("password") String password) {
		StudentSignUp studentSignUp = studentServiceImpl.authenticateUser(emailPhone,password);
		return studentSignUp;
	}
	
	@GetMapping("/getStudentDetails/{rollno}")
	public StudentDetails getStudentDetails(@PathVariable("rollno") String rollno) {
//		String jsonString = "{\"fullname\":\"asd\",\"contactno\":\"password\",\"rollno\":\"city\",\"semester\":\"emailPhone\",\"branch\":\"mobile\",\"previousSemResult\":\"PASS/ATKT\"}";
//		String listStr = "[{\"name\":\"asd\",\"password\":\"password\",\"city\":\"city\",\"emailPhone\":\"emailPhone\",\"mobile\":\"mobile\"},{\"name\":\"asd\",\"password\":\"password\",\"city\":\"city\",\"emailPhone\":\"emailPhone\",\"mobile\":\"mobile\"},{\"name\":\"asd\",\"password\":\"password\",\"city\":\"city\",\"emailPhone\":\"emailPhone\",\"mobile\":\"mobile\"}]";
		StudentDetails studentDetails = new StudentDetails();
		studentDetails = studentServiceImpl.getStudentDetailsByRollNo(rollno);
		return studentDetails;
	}
	
	
	@GetMapping("/getStudentDetails")
	public List<StudentDetails> getStudentDetails() {
		return studentServiceImpl.getAllStudentsList();
	}
	
	@PostMapping("/student_details")
	public StudentDetails saveStudentDetails(@RequestBody StudentDetails studentDetails) {
		return studentServiceImpl.saveStudentDetails(studentDetails);
	}
	
	@PostMapping("/updateAssignment")
	public Integer updateAssignment(@RequestBody StudentDetailsRequest studentDetailsRequest) {
		return studentServiceImpl.updateAssignment(studentDetailsRequest);
	}
	
	@PostMapping("/updateMarksJson")
	public Integer updateMarksJson(@RequestBody StudentDetailsRequest studentDetailsRequest) {
		return studentServiceImpl.updateMarksJson(studentDetailsRequest);
	}
	
	@GetMapping("/getAssignments")
	public List<StudentDetails> getAssignments() {
		List<StudentDetails> studentDetails = studentServiceImpl.getAssignments();
		return studentDetails;
	}
	
	@GetMapping("/getAssignmentsByRollno/{rollno}")
	public List<AssignmentDetailsDom> getAssignmentsByRollno(@PathVariable("rollno") String rollno) {
		List<AssignmentDetailsDom> assigments = assignmentDetailsServiceImpl.getAssignmentsByRollno(rollno);
		return assigments;
	}
	
	@PostMapping("/uploadNotes")
	public UploadNotesEntity uploadNotes(@RequestPart("file") MultipartFile file, @RequestPart("fileext") String fileext, @RequestPart("semTxt") String semTxt,@RequestPart("subject") String subject
			,@RequestPart("fileName") String fileName) throws IOException {
		return notesServiceImpl.uploadNotes(file, fileext, semTxt,subject,fileName);
	}
	
	@GetMapping("/getFile")
	public byte[] getFile1() {
		byte[] studentDetails = studentServiceImpl.getFile1();
		return studentDetails;
	}
	
	@GetMapping("/getStudentSubmittedAssignmentsByAssignmentName/{assignmentName}")
	public List<StudentDetails> getStudentSubmittedAssignmentsByAssignmentName(@PathVariable("assignmentName") String assignmentName) {
		List<StudentDetails> studentDetails = studentServiceImpl.getStudentSubmittedAssignmentsByAssignmentName(assignmentName);
		return studentDetails;
	}
	
	@GetMapping("/getStudSbmtdAssignmentFile/{fileId}")
	public AssignmentDetails getStudSbmtdAssignmentFile(@PathVariable("fileId") Integer fileId, @RequestParam("assgmntCode") Integer assgmntCode) {
		AssignmentDetails studSbmtdAssgnmtFile = studentServiceImpl.getStudSbmtdAssignmentFile(fileId,assgmntCode);
		return studSbmtdAssgnmtFile;
	}
	
	@PostMapping("/updateStudentAssignments")
	public Integer uploadAssgnments(@RequestPart("file") MultipartFile file, @RequestPart("fileext") String fileext, @RequestPart("rollno") String rollno,@RequestPart("code") String code) throws IOException {
		return assignmentDetailsServiceImpl.updateStudentAssignments(file, fileext, rollno, code);
	}
	
	
	@PostMapping("/getPosts")
	public String getSome(@RequestBody StudentDetailsRequest student) {
		return student.getTitle();
	}
	
	
	@GetMapping("/getAssignmentsDetlsByAssgnName/{assignmentName}")
	public List<AssignmentDetailsDom> getAssignmentsDetlsByAssgnName(@PathVariable("assignmentName") String assignmentName) {
		List<AssignmentDetailsDom> studentDetails = studentServiceImpl.getAssignmentsDetlsByAssgnName(assignmentName);
		return studentDetails;
	}
	
	@GetMapping("/getAssignmentsDetlsByAssgnCode/{assignmentCode}")
	public List<AssignmentDetailsDom> getAssignmentsDetlsByAssgnCode(@PathVariable("assignmentCode") Integer assignmentCode) {
		List<AssignmentDetailsDom> studentDetails = assignmentDetailsServiceImpl.getAssignmentsDetlsByAssgnCode(assignmentCode);
		return studentDetails;
	}
	
	
	@GetMapping("/updateAssignmentsToNewlyAddedStudents")
	public List<StudAssignmentDtl> updateAssignmentsToNewlyAddedStudents() {
		List<StudAssignmentDtl> studentDetails = studentServiceImpl.updateAssignmentsToNewlyAddedStudents();
		return studentDetails;
	}
	
	@GetMapping("/getTestMarksByRollno/{rollNoTest}")
	public String getTestMarksByRollno(@PathVariable("rollNoTest") Integer rollNoTest) {
		return studentServiceImpl.getTestMarksByRollno(rollNoTest);
	}
	
	@GetMapping("/assignMentorToRollNos")
	public int assignMentorToRollNos(@RequestParam("rollNos") String rollNos, @RequestParam("mentorName") String mentorName) {
		return studentServiceImpl.assignMentorToRollNos(rollNos, mentorName);
	}
	
	@GetMapping("/checkIfEnrlmntExists/{enrollmentId}")
	public int checkIfEnrlmntExists(@PathVariable("enrollmentId") String enrollmentId) {
		return studentServiceImpl.checkIfEnrlmntExists(enrollmentId);
	}
	
	@GetMapping("/checkIfRollNoExists/{rollNo}")
	public int checkIfRollNoExists(@PathVariable("rollNo") String rollNo) {
		return studentServiceImpl.checkIfRollNoExists(rollNo);
	}
	
	@GetMapping("/deleteStudentBySemesterRollno")
	public String deleteStudentBySemesterRollno(@RequestParam("semester") String semester,@RequestParam("rollno") Integer rollno) {
		return studentServiceImpl.deleteStudentBySemesterRollno(semester,rollno);
	}
	
	@GetMapping("/createDataBackup")
	public byte[] createDataBackup() throws IOException {
		File responseString = studentServiceImpl.createDataBackup();
		return Files.readAllBytes(Path.of(responseString.getPath()));
	}
	
	
	@GetMapping("/asd")
	public List<CustomClz> asd() {
		return studentServiceImpl.asd();
	}
	
	@GetMapping("/getAttendanceDetails/{rollno}")
	public List<CustomClz> getAttendanceDetails(@PathVariable("rollno") Integer rollno) {
		return studentServiceImpl.getAttendanceDetails(rollno);
	}
	
	@GetMapping("/getProjectMembersList/{rollno}")
	public List<CustomClz> getProjectMembersList(@PathVariable("rollno") Integer rollno) {
		return studentServiceImpl.getProjectMembersList(rollno);
	}
	
	
	@PostMapping("/submitMiniProjectDetails")
	public List<StudentDetails> submitMiniProjectDetails(@RequestBody MiniProjectDetails miniProjectDetails) {
		return studentServiceImpl.submitMiniProjectDetails(miniProjectDetails);
	}
	
	@GetMapping("/getMiniProjectDetails/{rollno}")
	public List<MiniProjectsWrapper> getMiniProjectDetails(@PathVariable("rollno") Integer rollno) {
		List<MiniProjectsWrapper> miniProjectsWrappers = new ArrayList<>();
		List<MiniProjectDetails> list =  miniProjectDetailsServiceImpl.getMiniProjectDetails(rollno);
		
		
		Set<Long> prjGrpIds = list.stream().map(i -> i.getGrpPrjId()).collect(Collectors.toSet());
		
		for(Long prgGrpId : prjGrpIds) {
			MiniProjectsWrapper miniProjectsWrapper = new MiniProjectsWrapper();
			List<MiniProjectDetails> l1 = new ArrayList<>();
			for(MiniProjectDetails obj : list) {
				if(prgGrpId.equals(obj.getGrpPrjId())){
					l1.add(obj);
				}
				
			}
			miniProjectsWrapper.setMiniProjectDetails(l1);
			miniProjectsWrappers.add(miniProjectsWrapper);
			list.removeIf(id -> prgGrpId.equals(id.getGrpPrjId()));
		}
		return miniProjectsWrappers;
		
	}
	
	
	@PostMapping("/uploadPrjFile")
	public Integer uploadPrjFile(@RequestPart("file") MultipartFile file, @RequestPart("rollno") String rollno,@RequestPart("subject") String subject) throws IOException {
		return miniProjectDetailsServiceImpl.uploadPrjFile(file, rollno, subject);
	}
	
	
	@GetMapping("/getIsProjectLeader/{rollno}")
	public Object[] getProjectLeader(@PathVariable("rollno") Integer rollno) {
		return miniProjectDetailsServiceImpl.getIsProjectLeader(rollno);
	}
	
	@PostMapping("/updateIsPrjectLeaderStatus")
	public int updateIsPrjectLeaderStatus(@RequestBody StudentDetailsRequest studentDetailsRequest) {
		return studentServiceImpl.updateIsPrjectLeaderStatus(studentDetailsRequest);
	}
	
}
