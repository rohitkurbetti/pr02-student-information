package com.example.workaholic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.workaholic.entity.MiniProjectDetails;

@Repository
@Transactional
public interface MiniProjectDetailsRepository extends JpaRepository<MiniProjectDetails, Integer>{

	@Query("select m2 from MiniProjectDetails m1 inner join MiniProjectDetails m2 on m1.grpPrjId =m2.grpPrjId and m1.projectSubject = m2.projectSubject"
			+ " where m1.rollno = :rollno ")
	List<MiniProjectDetails> getMiniPrjDetailsByRollNo(@Param("rollno") Integer rollno);


	@Transactional
	@Modifying
	@Query("update MiniProjectDetails set miniProjectFiles = :fileData where rollno=:rollno and projectSubject=:subject ")
	Integer uploadPrjFile(byte[] fileData, int rollno, String subject);


	@Query("select mpd.isProjectLeader,mpd.fullname,mpd.semester from StudentDetails mpd where rollno=:rollno ")
	Object[] getIsProjectLeader(Integer rollno);


	@Query("select m2.grpPrjId  from MiniProjectDetails m1 inner join MiniProjectDetails m2 on m1.grpPrjId =m2.grpPrjId and m1.projectSubject =m2.projectSubject "
			+ "where m1.rollno=:rollno "
			+ "group by m2.grpPrjId")
	List<Long> getPrjGrpIds(Integer rollno);

}
