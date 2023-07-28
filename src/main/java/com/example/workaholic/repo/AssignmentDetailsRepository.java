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

@Repository
public interface AssignmentDetailsRepository extends JpaRepository<AssignmentDetails, Integer>{
	
	
	@Query("select new com.example.workaholic.entity.AssignmentDetails(ad.code,ad.assignment) from AssignmentDetails ad where ad.rollno=:rollno")
	List<AssignmentDetails> getAssignmentsByRollno(@Param("rollno") Integer rollno);

	@Transactional
	@Modifying
	@Query("update AssignmentDetails set studentSubmittedAssignments = :studentSubmittedAssignments,assignmentStatus='SUBMITTED', file_ext=:fileExt where rollno=:rollno and code=:code ")
	Integer updateStudentAssignments(byte[] studentSubmittedAssignments, String fileExt, Integer rollno, Integer code);

	@Transactional
	@Query(" select new com.example.workaholic.entity.AssignmentDetailsDom(sd.rollno,sd.fullname,ad.code,ad.assignmentStatus,ad.studentSubmittedAssignments) "
			+ " from AssignmentDetails ad inner join StudentDetails sd on ad.rollno = sd.rollno and ad.branch =sd.branch and ad.semester =sd.semester "
			+ " where ad.code =:assignmentCode ")
	List<AssignmentDetailsDom> getAssignmentsDetlsByAssgnCode(@Param("assignmentCode") Integer assignmentCode);

	@Query(" select distinct(ad.code) from AssignmentDetails ad where branch=:branch and semester=:semester ")
	Integer getASsignmentCode(@Param("branch") String branch,@Param("semester") String semester);

	

}
