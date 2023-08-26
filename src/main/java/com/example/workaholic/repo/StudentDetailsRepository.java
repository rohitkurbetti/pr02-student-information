package com.example.workaholic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.workaholic.entity.AssignmentDetails;
import com.example.workaholic.entity.AssignmentDetailsDom;
import com.example.workaholic.entity.SomeMapper;
import com.example.workaholic.entity.StudAssignmentDtl;
import com.example.workaholic.entity.StudentDetails;

@Transactional
@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Integer>{

	@Transactional
	@Modifying
	@Query("update StudentDetails set assignmentName = :assignmentName, assignment = :assignment where lower(branch)=lower(:branch) and semester=:semester")
	int updateAssignmentByBranchSemester(@Param("assignmentName") String assignmentName, @Param("assignment") String assignment,@Param("branch") String branch, @Param("semester") String semester);

	@Query("select distinct sd from StudentDetails sd where sd.rollno=:rollno")
	StudentDetails findByRollNo(@Param("rollno") Integer rollno);

	@Transactional
	@Modifying
	@Query("update StudentDetails set marksJson = :marksJson where rollno=:rollno ")
	int updateMarksJsonByRollno(@Param("rollno") Integer integer,@Param("marksJson") String marksJson);

	@Transactional
	@Modifying
	@Query("update StudentDetails set fileNotes = :fileNotes, file_ext=:fileExt where semester=:semTxt")
	Integer uploadNotes(@Param("fileNotes") byte[] fileNotes,@Param("fileExt") String fileExt,@Param("semTxt") String semTxt);

	@Query("select sd.fileNotes from StudentDetails sd where sd.rollno=12")
	byte[] getFile1();

	@Query("select distinct new com.example.workaholic.entity.StudentDetails(sd.assignmentName) from StudentDetails sd")
	List<StudentDetails> getAssignments();

	@Query("select sd from StudentDetails sd where sd.assignmentName=:assignmentName")
	List<StudentDetails> getByAsssignmentName(String assignmentName);

	@Query("select ad from AssignmentDetails ad where ad.rollno=:rollno and ad.code=:assgmntCode")
	AssignmentDetails getStudSbmtdAssignmentFile(@Param("rollno") Integer fileId,@Param("assgmntCode") Integer assgmntCode);
	
//	@Query("select sd.assignment from StudentDetails sd where sd.rollno=:rollno")
//	String getAssignmentsByRollno(@Param("rollno") Integer rollno);

//	@Transactional
//	@Modifying
//	@Query("update StudentDetails set studentSubmittedAssignments = :studentSubmittedAssignments, file_ext=:fileExt where rollno=:rollno")
//	Integer updateStudentAssignments(byte[] studentSubmittedAssignments, String fileExt, int rollno);

	
	@Query("select sd.rollno from StudentDetails sd where lower(sd.branch)=lower(:branch) and sd.semester=:semester")
	List<Integer> extractRollNoListByBranchSemester(@Param("branch") String branch,@Param("semester") String semester);

	
	@Query(" select new com.example.workaholic.entity.AssignmentDetailsDom(sd.rollno,sd.fullname,sd.assignmentName,ad.code,ad.assignmentStatus) "
			+ " from AssignmentDetails ad inner join StudentDetails sd on ad.rollno = sd.rollno and lower(ad.branch) =lower(sd.branch) and ad.semester =sd.semester "
			+ " where sd.assignmentName =:assignmentName ")
	List<AssignmentDetailsDom> getByAsssignmentDetailsByAssgnName(@Param("assignmentName") String assignmentName);

	@Query(" select distinct new com.example.workaholic.entity.StudAssignmentDtl(sd.assignment, sd.assignmentName,sd.branch, sd.semester) from StudentDetails sd where assignment is not null and assignment_name is not null ")
	List<StudAssignmentDtl> updateAssignmentsToNewlyAddedStudents();

	@Transactional
	@Modifying
	@Query("update StudentDetails sd set sd.assignment=:assignment,sd.assignmentName = :assignmentName where lower(sd.branch)=lower(:branch) and sd.semester=:semester and sd.assignment is null and sd.assignmentName is null ")
	int updateASsignToNullPlaces(String branch, String semester, String assignmentName, String assignment);

	
	@Query("select distinct new com.example.workaholic.entity.SomeMapper(sd.assignment ,sd.branch ,sd.semester ,sd.rollno) from StudentDetails sd left join AssignmentDetails ad on lower(sd.branch) = lower(ad.branch) and sd.semester =ad.semester and sd.rollno =ad.rollno "
			+ " where lower(sd.branch) =lower(:branch) and sd.semester =:semester and not exists (select 1 from AssignmentDetails ad2 where ad2.rollno=sd.rollno) ")
	List<SomeMapper> addDeltaAssignments(@Param("branch") String branch,@Param("semester") String semester);

	
	@Query("select sd.marksJson from StudentDetails sd where sd.rollno=:rollNoTest")
	String getTestMarksByRollno(@Param("rollNoTest") Integer rollNoTest);

	
	@Transactional
	@Modifying
	@Query("update StudentDetails sd set sd.mentorName=:mentorName where sd.rollno in (:rollNos) ")
	int assignMentorToRollNos(@Param("rollNos") Integer[] rollNos,@Param("mentorName") String mentorName);

	
	@Query("select count(sd) from StudentDetails sd where sd.enrollmentId=:enrollmentId")
	int checkIfEnrlmntExists(@Param("enrollmentId") String enrollmentId);

	@Transactional
	@Modifying
	@Query("delete from StudentDetails sd where sd.semester = :semester and sd.rollno = :rollno")
	int deleteStudentDetailsBySemesterRollno(@Param("semester") String semester, @Param("rollno") Integer rollno);

	
	@Query("select sd from StudentDetails sd ")
	List<StudentDetails> getAllStudents();

	
	
	@Query("select count(sd) from StudentDetails sd where sd.rollno=:rollno")
	int checkIfRollNoExists(@Param("rollno") Integer rollno);

	

//	@Query(" select distinct new com.example.workaholic.entity.StudAssignmentDtl(sd.assignment, sd.assignmentName) from StudentDetails sd where assignment is not null and assignment_name is not null ")
//	void getAssignmentAndName(String barnch, String sem);

	

}
