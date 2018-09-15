package com.survey.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.survey.app.model.UrCode;

@Repository
public interface UrCodeRepository extends JpaRepository<UrCode, Long> {
    
    @Query("SELECT d FROM UrCode d WHERE d.uCode = :uCode")
	UrCode findUrcodeByUrcode(@Param("uCode")Long uCode);

    
}
