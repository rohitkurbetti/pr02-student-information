package com.example.workaholic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.workaholic.entity.StudentSignUp;
import com.example.workaholic.entity.TestMarksEntity;

@Transactional
@Repository
public interface TestMarksRepository extends JpaRepository<TestMarksEntity, Long>{

	
	
	@Query(" select distinct(tm) from TestMarksEntity tm where tm.rollno =:rollno ")
	List<TestMarksEntity> getStudentMarksByRollNo(@Param("rollno") Integer rollno);

	
	@Transactional
	@Modifying
	@Query("delete from TestMarksEntity tm where tm.testMarksId = :testMarksId")
	int deleteStudMarksById(@Param("testMarksId") Long testMarksId);

	
	
	
}
