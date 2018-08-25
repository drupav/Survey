package com.survey.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.survey.app.model.District;
import com.survey.app.model.FileUpload;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

}
