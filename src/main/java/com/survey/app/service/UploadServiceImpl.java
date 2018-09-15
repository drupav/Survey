package com.survey.app.service;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Optional;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.survey.app.dao.UploadDAO;
import com.survey.app.dto.UploadData;
import com.survey.app.exception.MyFileNotFoundException;
import com.survey.app.model.FileUpload;
import com.survey.app.repository.FileUploadRepository;
import com.survey.app.util.FileStorageProperties;
import com.survey.app.util.FileUtils;
import com.survey.app.util.Page;

@Service
public class UploadServiceImpl implements UploadService{
	private  Path fileStorageLocation;
	
	@Autowired
	private FileUploadRepository fileUploadRepository;
	
	@Autowired
	private QualityCheckService  qualityCheckService;
	
	@Autowired
    JobLauncher simpleJobLauncher;
	
	@Autowired
    Job processJob;
	
	@Autowired
	private UploadDAO uploadDAO; 
	
	@Autowired
    public UploadServiceImpl(FileStorageProperties fileStorageProperties) {
		
	}
	

	@Override
	public String storeFile(MultipartFile file,String comments,String uploadType)  throws Exception {
		// Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
       //User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.fileStorageLocation = Paths.get(FileUtils.generateXlsFileDirectory())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
        	
        }
    
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileAlreadyExistsException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            FileUpload fileUpload = new FileUpload();
            fileUpload.setFileName(fileName);
            fileUpload.setFileLocation(targetLocation.toAbsolutePath().toString());
            fileUpload.setUploadDate(new  Date());
            fileUpload.setUploadType(uploadType);
            fileUpload.setComments(comments);
            fileUploadRepository.save(fileUpload);
            
            if("QUALITY_CHECK".equalsIgnoreCase(uploadType)){
            	qualityCheckService.processQualityCheckRecord(fileUpload.getFileLocation());
            }else{
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
            		.addString("filelocation", fileUpload.getFileLocation())
                    .toJobParameters();
            simpleJobLauncher.run(processJob, jobParameters);
            }
 
           // return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (IOException ex) {}
		return fileName;
	}

	@Override
	public Page<UploadData> getAllUploads(Long pageNum, Long pageSize) {
		return uploadDAO.findAllUploads(pageNum, pageSize);
	}


	@Override
	public FileUpload findUploadfileById(Long id) {
		Optional<FileUpload> optional = fileUploadRepository.findById(id);
		return optional.isPresent()?optional.get():null;
		
	}


	@Override
	public Resource loadFileAsResource(Long fileId) {
		 this.fileStorageLocation = Paths.get(FileUtils.generateXlsFileDirectory())
	                .toAbsolutePath().normalize();
		 FileUpload fileUpload = findUploadfileById(fileId);
		 
	        try {
	            Files.createDirectories(this.fileStorageLocation);
	            Path filePath = this.fileStorageLocation.resolve(fileUpload.getFileLocation()).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new MyFileNotFoundException("File not found " + fileUpload.getFileName());
	            }
	        } catch (Exception ex) {
	        	
	        }
	        return null;
	}

}
