package com.survey.app.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.survey.app.dto.UploadData;
import com.survey.app.model.FileUpload;
import com.survey.app.util.Page;

public interface UploadService {
	
String storeFile(MultipartFile file, String comments) throws Exception;

Page<UploadData> getAllUploads(Long page, Long size);

FileUpload findUploadfileById(Long id);

Resource loadFileAsResource(Long fileId);

}
