package com.survey.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey.app.model.QualityCheck;

@Repository
public interface QualityCheckRepository extends JpaRepository<QualityCheck, Long> {

}
