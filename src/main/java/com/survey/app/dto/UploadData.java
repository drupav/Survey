package com.survey.app.dto;

import java.util.Date;

import org.joda.time.LocalDate;

public class UploadData {
    private Long id;
    private String fileName;
    private Date uploadDate;
    private String uploadBy;
    private String fileLocation;
	public UploadData(Long id, String fileName, String fileLocation, LocalDate uploadDate, String userName) {
		this.id = id;
		this.fileName = fileName;
		this.uploadDate =uploadDate.toDate();
		this.uploadBy = userName;
		this.fileLocation  = fileLocation;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getUploadBy() {
		return uploadBy;
	}
	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
}