package com.survey.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey.app.model.Respondent;

@Repository
public interface RespondentRepository extends JpaRepository<Respondent, Long> {

}
