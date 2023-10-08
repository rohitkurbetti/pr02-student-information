package com.example.workaholic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.workaholic.entity.AttendanceEntity;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer> {

	@Transactional
	@Modifying
	@Query("delete from AttendanceEntity ae where ae.attendanceDetailsId = :attendanceDetailsId")
	int delAttendanceById(@Param("attendanceDetailsId") Integer attendanceDetailsId);

	
	@Query("select ae from AttendanceEntity ae where ae.rollno = :rollno")
	List<AttendanceEntity> getByRollNo(@Param("rollno") Integer rollno);


	
	@Query("select ae.subject ,avg(ae.attendancePercent) from AttendanceEntity ae inner join StudentDetails sd "
			+ " on ae.rollno = sd.rollno where ae.rollno = :rollno group by ae.subject ")
	List<Object[]> getAttendanceAvg(@Param("rollno") Integer rollno);

}
