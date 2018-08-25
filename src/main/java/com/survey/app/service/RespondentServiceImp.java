package com.survey.app.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.survey.app.dao.RespondentDao;
import com.survey.app.dto.RespondentData;
import com.survey.app.exception.MyFileNotFoundException;
import com.survey.app.model.FileUpload;
import com.survey.app.model.Respondent;
import com.survey.app.repository.RespondentRepository;
import com.survey.app.util.FileStorageProperties;
import com.survey.app.util.FileUtils;
import com.survey.app.util.Page;

@Service
public class RespondentServiceImp implements RespondentService{
	
	private  Path fileStorageLocation;
	
	@Autowired
	private RespondentDao respondentDao;
	
	@Autowired
	private RespondentRepository respondentRepository;

	@Autowired
    Job processJob;
	
	@Autowired
    JobLauncher simpleJobLauncher;
	
	
	@Autowired
    public RespondentServiceImp(FileStorageProperties fileStorageProperties) {}
	
	@Override
	public Page<RespondentData> getAllRespondents(Long blockId, Long districtId,Long regionId, Long interviewerId,String searchString,Long pageNum, Long pageSize) {
		return respondentDao.findAllRespondents(blockId, districtId, regionId,interviewerId, searchString, pageNum, pageSize);
	}


	@Override
	public Resource loadFileAsResource(Long respondentId) {
		 this.fileStorageLocation = Paths.get(FileUtils.generateXlsFileDirectory())
	                .toAbsolutePath().normalize();
		 Optional<Respondent> fileUpload = respondentRepository.findById(respondentId);
		 
	        try {
	            Files.createDirectories(this.fileStorageLocation);
	            Path filePath = this.fileStorageLocation.resolve(fileUpload.get().getAudio()).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new MyFileNotFoundException("Audio File not found " + fileUpload.get().getAudio());
	            }
	        } catch (Exception ex) {
	        	
	        }
	        return null;
	}

	

}
