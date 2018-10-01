package com.survey.app.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.survey.app.dao.RespondentDao;
import com.survey.app.dto.AllSubmissionMetaData;
import com.survey.app.dto.InterviewerData;
import com.survey.app.dto.RespondentData;
import com.survey.app.dto.TreeViewData;
import com.survey.app.exception.MyFileNotFoundException;
import com.survey.app.model.District;
import com.survey.app.model.Respondent;
import com.survey.app.repository.DistrictRepository;
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
	private DistrictRepository districtRepository;

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

	@Override
	public TreeViewData getMetaData(Long page, Long size,Long blockId, Long districtId) {
		TreeViewData treeViewData = respondentDao.getMetaData(page, size,blockId,districtId);
		 AtomicInteger atomicInteger = new AtomicInteger(200);
		treeViewData.getItems().forEach(item ->{
			if(item.getDistrictId() != null)
			item.setId(Long.valueOf(atomicInteger.getAndIncrement()));
		});
		if(districtId != 0){
		District district = districtRepository.findById(districtId).get();
			List<AllSubmissionMetaData> list =	treeViewData.getItems().stream().
					filter( item -> 
					(item.getDistrictId() != null && item.getDistrictId() == districtId) || 
					(item.getDistrictId() == null && StringUtils.isNotEmpty(item.getDistrictName())
					&& district.getDistrictName().equalsIgnoreCase(item.getDistrictName()))).collect(Collectors.toList());
			treeViewData.setItems(list);
		}
		Long completed=treeViewData.getItems().stream().filter(item -> item.getDistrictId() == null).mapToLong(AllSubmissionMetaData::getCompletedSamples).sum();
		treeViewData.setTotalFilteredRecords(completed);
		treeViewData.setPendingSamples(treeViewData.getTotalSamples() - completed);
		return treeViewData;
	}

	@Override
	public Page<InterviewerData> getInterviewerData(Long blockId, Long districtId, String searchString, Long page,
			Long size) {
		return respondentDao.getInterviewerData(blockId, districtId, searchString, page, size);
	}

	@Override
	public Page<InterviewerData> getinterviewierQualitycheckData(Long blockId, Long districtId, String searchString,
			Long page, Long size) {
		return respondentDao.getinterviewierQualitycheckData(blockId, districtId, searchString, page, size);
	}

	

}
