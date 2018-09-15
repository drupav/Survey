package com.survey.app.dto;

import java.util.Date;

import org.joda.time.LocalDate;

public class UploadData {
    private Long id;
    private String fileName;
    private Date uploadDate;
    private String uploadBy;
    private String fileLocation; 
    private String uploadType;
    private String comments;
	public UploadData(Long id, String fileName, String fileLocation, LocalDate uploadDate, String userName, String comments,String  uploadType) {
		this.id = id;
		this.fileName = fileName;
		this.uploadDate =uploadDate.toDate();
		this.uploadBy = userName;
		this.fileLocation  = fileLocation;
		this.comments = comments;
		this.uploadType = uploadType;
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
	
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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