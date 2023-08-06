package com.example.workaholic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.workaholic.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	@Query(" select ue from UserEntity ue where ue.email=:email and ue.password=:password ")
	UserEntity authenticateUser(String email, String password);

	
	@Transactional
	@Modifying
	@Query("update UserEntity ue set ue.userRollno= :userRollno where email=:email ")
	Integer updateRollnoToUser(@Param("email") String email,@Param("userRollno") Integer userRollno);

}
