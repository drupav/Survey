package com.survey.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey.app.model.Interviewer;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {

    List<Interviewer>  findAll();
}
