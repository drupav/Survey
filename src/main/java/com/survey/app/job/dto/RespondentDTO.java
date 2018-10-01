package com.survey.app.job.dto;

import java.util.Date;

public class RespondentDTO {
	private Long serialNumber;
	private Date submissionDate;
	private Date startTime;
	private Date endTime;
	private Long duration;
    private String awcCode;
    private String awcName;
    private Long districtId;
    private Long blockId;
    private String interviewerName;
    private String interviewerCode;
    private String villageName;
    private Long sampleNum;
    private String audio;
	private String respondentName;
	private String hscName;
	private Long wardId;
	private Long urCode;
	private String address;
	private String contactNum;
	private String householdRel;
	private String result;
	 
	public Date getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public String getAwcCode() {
		return awcCode;
	}
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getInterviewerCode() {
		return interviewerCode;
	}
	public void setInterviewerCode(String interviewerCode) {
		this.interviewerCode = interviewerCode;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getAwcName() {
		return awcName;
	}
	public void setAwcName(String awcName) {
		this.awcName = awcName;
	}
	public Long getUrCode() {
		return urCode;
	}
	public void setUrCode(Long urCode) {
		this.urCode = urCode;
	}
	public void setAwcCode(String awcCode) {
		this.awcCode = awcCode;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getBlockId() {
		return blockId;
	}
	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}
	
	
	
	public String getInterviewerName() {
		return interviewerName;
	}
	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public Long getSampleNum() {
		return sampleNum;
	}
	public void setSampleNum(Long sampleNum) {
		this.sampleNum = sampleNum;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public String getRespondentName() {
		return respondentName;
	}
	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}
	public String getHscName() {
		return hscName;
	}
	public void setHscName(String hscName) {
		this.hscName = hscName;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getHouseholdRel() {
		return householdRel;
	}
	public void setHouseholdRel(String householdRel) {
		this.householdRel = householdRel;
	}
   



	
}