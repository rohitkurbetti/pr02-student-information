package com.example.workaholic.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.workaholic.controller.StudentDetailsRequest;
import com.example.workaholic.entity.AssignmentDetails;
import com.example.workaholic.entity.AssignmentDetailsDom;
import com.example.workaholic.entity.AttendanceEntity;
import com.example.workaholic.entity.MiniProjectDetails;
import com.example.workaholic.entity.SomeMapper;
import com.example.workaholic.entity.StudAssignmentDtl;
import com.example.workaholic.entity.StudentDetails;
import com.example.workaholic.entity.StudentSignUp;
import com.example.workaholic.entity.TestMarksEntity;
import com.example.workaholic.entity.UploadNotesEntity;
import com.example.workaholic.entity.UserEntity;
import com.example.workaholic.repo.AssignmentDetailsRepository;
import com.example.workaholic.repo.AttendanceRepository;
import com.example.workaholic.repo.CustomClz;
import com.example.workaholic.repo.MiniProjectDetailsRepository;
import com.example.workaholic.repo.NotesRepository;
import com.example.workaholic.repo.StudentDetailsRepository;
import com.example.workaholic.repo.StudentSignupRepo;
import com.example.workaholic.repo.TestMarksRepository;
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

	@Autowired
	private TestMarksRepository testMarksRepository;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private MiniProjectDetailsRepository miniProjectDetailsRepository;
	
	private File file;

	public StudentSignUp save(StudentSignUp student) {
		return studentSignupRepo.save(student);
	}

	public StudentSignUp authenticateUser(String emailPhone, String password) {
		StudentSignUp studentSignUp = studentSignupRepo.getEntryStudent(emailPhone);

		if (studentSignUp != null) {
			if (studentSignUp.getPassword().equals(password)) {
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
		if (queListStud != null && !queListStud.isEmpty()) {
			String questListStr = queListStud.stream().map(Object::toString).collect(Collectors.joining(", "));
			studentDetailsRepository.updateAssignmentByBranchSemester(studentDetailsRequest.getAssignmentName(),
					questListStr, studentDetailsRequest.getBranch(), studentDetailsRequest.getSemester());
		}
		List<Integer> rollno = extractRollNoListByBranchSemester(studentDetailsRequest);
		int counter = 0;
		for (Integer integer : rollno) {
			AssignmentDetails assignmentDetails = new AssignmentDetails();
			assignmentDetails.setBranch(studentDetailsRequest.getBranch());
			assignmentDetails.setSemester(studentDetailsRequest.getSemester());
			assignmentDetails.setRollno(integer);
			assignmentDetails.setCode(studentDetailsRequest.getCode());
			assignmentDetails.setAssignmentStatus("NOTSUBMITTED");
			List<String> queList = studentDetailsRequest.getQueList();
			if (queList != null && !queList.isEmpty()) {
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
		return studentDetailsRepository.updateMarksJsonByRollno(studentDetailsRequest.getRollno(),
				studentDetailsRequest.getMarksJson());
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
		return studentDetailsRepository.getStudSbmtdAssignmentFile(fileId, assgmntCode);
	}

	public List<AssignmentDetailsDom> getAssignmentsDetlsByAssgnName(String assignmentName) {
		return studentDetailsRepository.getByAsssignmentDetailsByAssgnName(assignmentName);
	}

	public List<StudAssignmentDtl> updateAssignmentsToNewlyAddedStudents() {

		List<StudAssignmentDtl> studAssgnDtl = studentDetailsRepository.updateAssignmentsToNewlyAddedStudents();

		studAssgnDtl.forEach(e -> {
			String barnch = e.getBranch();
			String sem = e.getSemester();
			String assgnment = e.getAssignment();
			String assgnname = e.getAssignmentName();
			studentDetailsRepository.updateASsignToNullPlaces(barnch, sem, assgnname, assgnment);

			List<SomeMapper> mapper = studentDetailsRepository.addDeltaAssignments(barnch, sem);
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

		if (studentList != null && studentList.size() > 0) {
			studentList.forEach(student -> {
				String email = userRepository.getEmailByRollNo(student.getRollno());
				student.setEmail(email);
			});
		}
		return studentList;
	}

	public String deleteStudentBySemesterRollno(String semester, Integer rollno) {

		int rowsDeletedStudentDetails = studentDetailsRepository.deleteStudentDetailsBySemesterRollno(semester, rollno);

		int rowsDeletedAssignmentDetails = assignmentDetailsRepository.deleteAssignmentDetailsBySemesterRollno(semester,
				rollno);

		int rowsDeletedUserDetails = userRepository.deleteUserDetailsByRollno(rollno);

		int rowsTestMarkDetails = testMarksRepository.deleteTestMarkDetailsByRollno(rollno);

		StringBuilder sb = new StringBuilder();

		sb.append("studentDetiails: ");
		sb.append(rowsDeletedStudentDetails);
		sb.append(",");
		sb.append("assignment details: ");
		sb.append(rowsDeletedAssignmentDetails);
		sb.append(",");
		sb.append("user details: ");
		sb.append(rowsDeletedUserDetails);
		sb.append(",");
		sb.append("testmarks details: ");
		sb.append(rowsTestMarkDetails);

		return sb.toString();
	}

	public int checkIfRollNoExists(String rollNo) {
		return studentDetailsRepository.checkIfRollNoExists(Integer.parseInt(rollNo));
	}

	public File createDataBackup() throws IOException {

		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			
			String dateTIme = sdf.format(date.getTime());
			
			File dir = new File("backup");
			
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			file = new File("backup","Backup_"+dateTIme+".txt");
			FileWriter fileWriter = new FileWriter(file, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			//student details table
			List<StudentDetails> studentDetailsList = studentDetailsRepository.getAllStudents();
			studentDetailsList.forEach(sd -> {

				String studentDetails = "INSERT INTO public.student_details"
						+ "(student_details_id, \"assignment\", assignment_name, branch, contactno, enrollment_id, file_ext, file_notes,"
						+ "fullname, marks_json, mentor_name, previous_sem_result, rollno, semester, student_submitted_assignments, is_team_assigned, is_project_leader)"
						+ " VALUES('" + sd.getStudentDetailsId() + "', '" + sd.getAssignment() + "', '"
						+ sd.getAssignmentName() + "', " + "'" + sd.getBranch() + "', '" + sd.getContactno() + "', '"
						+ sd.getEnrollmentId() + "', NULL, NULL," + "'" + sd.getFullname() + "', NULL, NULL, '"
						+ sd.getPreviousSemResult() + "', " + sd.getRollno() + ", '" + sd.getSemester() + "', NULL, '"+ sd.getIsTeamAssigned() +"', '"+ sd.getIsProjectLeader() +"');";

				// Write the text to the file
				try {
					bufferedWriter.write(studentDetails);
					bufferedWriter.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			
			List<AssignmentDetails> assignmentDetailsList = assignmentDetailsRepository.findAll();
			
			bufferedWriter.newLine();
			assignmentDetailsList.forEach(ad -> {
				
				String assignmentDetails = "INSERT INTO public.assignment_details"
						+ "(assignment_details_id, \"assignment\", assignment_status, branch, code, file_ext, rollno, semester, student_submitted_assignments)"
						+ " VALUES("+ad.getAssignmentDetailsId()+", '"+ad.getAssignment()+"', '"+ad.getAssignmentStatus()+"', '"+ad.getBranch()+"', "
						+ ""+ad.getCode()+", NULL, "+ad.getRollno()+", '"+ad.getSemester()+"', NULL);";
				try {
					bufferedWriter.write(assignmentDetails);
					bufferedWriter.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});
			
			
//			testMarksRepository
			
			List<TestMarksEntity> testMarksEntity = testMarksRepository.findAll();
			bufferedWriter.newLine();
			testMarksEntity.forEach(tm -> {
				
				String testMarks = "INSERT INTO public.test_marks"
						+ "(test_marks_id, marks, rollno, subject, test)"
						+ "VALUES("+tm.getTestMarksId()+", "+tm.getMarks()+", "+tm.getRollno()+", '"+tm.getSubject()+"', '"+tm.getTest()+"');";
				
				try {
					bufferedWriter.write(testMarks);
					bufferedWriter.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});
			
//			userRepository
			
			
			List<UserEntity> userEntities = userRepository.findAll();
			bufferedWriter.newLine();
			userEntities.forEach(ue -> {
				
				String userEntity = "INSERT INTO public.user_details"
						+ "(user_details_id, email, fullname, \"password\", user_rollno)"
						+ " VALUES("+ue.getUserDetailsId()+", '"+ue.getEmail()+"', '"+ue.getFullname()+"', '"+ue.getPassword()+"', "+ue.getUserRollno()+");";
				
				try {
					bufferedWriter.write(userEntity);
					bufferedWriter.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});
			
			
			
			List<AttendanceEntity> attenList = attendanceRepository.findAll();
			bufferedWriter.newLine();
			attenList.forEach(ae -> {
				
				String attEntity = "INSERT INTO public.attendance_details"
						+ "(attendance_details_id, attendance_percent, lectures_attended, \"month\", rollno, total_lectures)"
						+ " VALUES("+ae.getAttendanceDetailsId()+", "+ae.getAttendancePercent()+", "+ae.getLecturesAttended()+","
						+ " "+ae.getMonth()+", "+ae.getRollno()+", "+ae.getTotalLectures()+");";
				
				try {
					bufferedWriter.write(attEntity);
					bufferedWriter.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});
			
			
			List<UploadNotesEntity> notesList = notesRepository.findAll();
			bufferedWriter.newLine();
			notesList.forEach(nl -> {
				
				String notes = "INSERT INTO public.note_details"
						+ "(note_details_id, branch, file_ext, file_notes, filename, semester, subject)"
						+ " VALUES("+nl.getNoteDetailsId()+", '"+nl.getBranch()+"', '"+nl.getFileExt()+"', "
						+ " NULL, '"+nl.getFilename()+"', '"+nl.getSemester()+"', '"+nl.getSubject()+"');";
				
				try {
					bufferedWriter.write(notes);
					bufferedWriter.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});
			
			List<MiniProjectDetails> miniPrjList = miniProjectDetailsRepository.findAll();
			bufferedWriter.newLine();

			miniPrjList.forEach(sd -> {

				String miniPrjDetails = "INSERT INTO public.mini_project_details "
						+ "	(mini_project_details_id, grp_prj_id, is_project_leader, mini_project_files, project_title, project_member_name, project_subject, semester, rollno) "
						+ "	VALUES('"+sd.getMiniProjectDetailsId()+"', '"+sd.getGrpPrjId()+"', '"+sd.getIsProjectLeader()+"', NULL, '"+sd.getPrjTitle()+"', '"+sd.getProjectMemberName()+"', '"+sd.getProjectSubject()+"', '"+sd.getSemester()+"', '"+sd.getRollno()+"');";

				// Write the text to the file
				try {
					bufferedWriter.write(miniPrjDetails);
					bufferedWriter.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			

			

			

			// Close the BufferedWriter
			bufferedWriter.close();

		} catch (Exception e) {

		}
		return file;
	}

	public List<CustomClz> asd() {
		List<CustomClz> customClzList = new ArrayList<>();
		List<StudentDetails> studList = studentDetailsRepository.findAll();
		
		studList.forEach(s -> {
			CustomClz customClz = new CustomClz();
			Integer rollno = s.getRollno();
			List<AttendanceEntity> attendanceList = attendanceRepository.getByRollNo(rollno);
			customClz.setRollno(rollno);
			customClz.setFullname(s.getFullname());
			customClz.setAttendanceEntity(attendanceList);
			customClzList.add(customClz);
		});
		return customClzList;
	}

	
	public List<CustomClz> getAttendanceDetails(Integer rollno) {
		List<CustomClz> customClzList = new ArrayList<>();
		StudentDetails studDet = studentDetailsRepository.getStudentDetailsByRollNo(rollno);
		CustomClz customClz = new CustomClz();
		List<AttendanceEntity> attendanceList = attendanceRepository.getByRollNo(rollno);
		customClz.setRollno(rollno);
		customClz.setFullname(studDet.getFullname());
		customClz.setAttendanceEntity(attendanceList);
		customClzList.add(customClz);
		return customClzList;
	}

	public List<CustomClz> getProjectMembersList(Integer rollno) {
		List<CustomClz> studDet = studentDetailsRepository.getProjectMembersList(rollno);
		return studDet;
	}

	public List<StudentDetails> submitMiniProjectDetails(MiniProjectDetails miniProjectDetails) {
		
		List<Long> prjMembersRollnoList = miniProjectDetails.getProjectMemberRollNosList();
		
		prjMembersRollnoList.add(miniProjectDetails.getProjLeaderRollno());
		List<Integer> rollNos = prjMembersRollnoList.stream().map(Long::intValue).collect(Collectors.toList());
		List<StudentDetails> studList =  studentDetailsRepository.getStudentDetailsByRollNoMultiple(rollNos);
		
		
		
		for (StudentDetails student : studList) {
			MiniProjectDetails miniProjectEntity = new MiniProjectDetails();
			miniProjectEntity.setProjectMemberName(student.getFullname());
			miniProjectEntity.setGrpPrjId(miniProjectDetails.getGrpPrjId());
			if(miniProjectDetails.getProjLeaderRollno().equals(Long.valueOf(student.getRollno()))) {				
				miniProjectEntity.setIsProjectLeader(true);
			}
			miniProjectEntity.setPrjTitle(miniProjectDetails.getPrjTitle());
			miniProjectEntity.setProjectSubject(miniProjectDetails.getProjectSubject());
			miniProjectEntity.setSemester(student.getSemester());
			miniProjectEntity.setRollno(student.getRollno());
			miniProjectEntity = miniProjectDetailsRepository.save(miniProjectEntity);
			
			if(miniProjectEntity.getMiniProjectDetailsId() != null) {
				student.setIsTeamAssigned(true);
				studentDetailsRepository.save(student);
			}
		}
		
		
		
		
		return studList;
	}

	public int updateIsPrjectLeaderStatus(StudentDetailsRequest studentDetailsRequest) {
		Integer rollno = studentDetailsRequest.getRollno();
		Boolean isProjectLeader = studentDetailsRequest.getIsProjectLeader();
		return studentDetailsRepository.updateIsPrjectLeaderStatus(rollno, isProjectLeader);
	}
	
}
