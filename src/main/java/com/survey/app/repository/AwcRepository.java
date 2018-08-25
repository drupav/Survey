package com.survey.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.survey.app.model.Awc;

@Repository
public interface AwcRepository extends JpaRepository<Awc, Long> {

    List<Awc>  findAll();
    
    @Query("SELECT d FROM Awc d WHERE d.awcCode = :awcCode")
	Awc findAwcByAwcCode(String awcCode);

}
