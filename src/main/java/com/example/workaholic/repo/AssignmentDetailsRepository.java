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
import com.example.workaholic.entity.SubmtdAssignmentsDom;

@Repository
public interface AssignmentDetailsRepository extends JpaRepository<AssignmentDetails, Integer>{
	
	
	@Query("select distinct new com.example.workaholic.entity.AssignmentDetailsDom(sd.rollno, sd.assignmentName, ad.assignment, ad.code ) "
			+ " from AssignmentDetails ad inner join StudentDetails sd on ad.branch = sd.branch and ad.semester = sd.semester and ad.rollno = sd.rollno "
			+ "	where ad.rollno=:rollno ")
	List<AssignmentDetailsDom> getAssignmentsByRollno(@Param("rollno") Integer rollno);

	@Transactional
	@Modifying
	@Query("update AssignmentDetails set studentSubmittedAssignments = :studentSubmittedAssignments,assignmentStatus='SUBMITTED', file_ext=:fileExt where rollno=:rollno and code=:code ")
	Integer updateStudentAssignments(byte[] studentSubmittedAssignments, String fileExt, Integer rollno, Integer code);

	@Transactional
	@Query(" select new com.example.workaholic.entity.AssignmentDetailsDom(sd.rollno,sd.fullname,ad.code,ad.assignmentStatus) "
			+ " from AssignmentDetails ad inner join StudentDetails sd on ad.rollno = sd.rollno and ad.branch =sd.branch and ad.semester =sd.semester "
			+ " where ad.code =:assignmentCode ")
	List<AssignmentDetailsDom> getAssignmentsDetlsByAssgnCode(@Param("assignmentCode") Integer assignmentCode);

	@Query(" select distinct(ad.code) from AssignmentDetails ad where lower(branch)=lower(:branch) and semester=:semester ")
	List<Integer> getASsignmentCode(@Param("branch") String branch,@Param("semester") String semester);

	@Query(" select new com.example.workaholic.entity.SubmtdAssignmentsDom(sd.rollno, sd.assignmentName, ad.code, ad.assignmentStatus) "
			+ " from AssignmentDetails ad inner join StudentDetails sd on ad.branch = sd.branch and ad.semester = sd.semester and ad.rollno = sd.rollno "
			+ " where ad.rollno=:rollno and ad.assignmentStatus='SUBMITTED' ")
	List<SubmtdAssignmentsDom> getSubmittedAssignmentsByRollNo(@Param("rollno") Integer rollNo);

	@Transactional
	@Modifying
	@Query("delete from AssignmentDetails ad where ad.semester =:semester and ad.rollno = :rollno")
	int deleteAssignmentDetailsBySemesterRollno(@Param("semester") String semester,@Param("rollno") Integer rollno);

	

}
