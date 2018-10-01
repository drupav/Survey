package com.survey.app.dto;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.survey.app.model.Block;
import com.survey.app.model.District;
import com.survey.app.model.Interviewer;
import com.survey.app.model.Region;

public class RespondentData {
    private Long id;
    private String awcName;
    private String regionName;
    private String districtName;
    private String blockName;
    private String interviewerName;
    private String villageName;
    private Long sampleNum;
    private String audio;
    private String householdRel;
	private String respondentName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date  lastSubmission;
	private List<Region> regions;
	private List<District> districts;
	private List<Block> blocks;
	private String hscName;
	private String ward;
	private String address;
	private String contactNum;
	private String resultStatus;
	private List<Interviewer> interviewers;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:MM")
	private Date startTime;
	private Long duration;
	private String awcCode;
	private String urName;
   


	public RespondentData(Long id, String regionName, String districtName,String blockName, String villageName, String interviewerName,
			String respondentName, String audio, Long samplenum,LocalDate lastSubmission, String awcName, String hscName, String ward, 
			String address, String contactNum, String resultStatus, DateTime startTime, Long duration, String awcCode, String urName) {
		this.id =id;
		this.regionName =regionName;
		this.districtName = districtName;
		this.blockName = blockName;
		this.villageName = villageName;
		this.interviewerName = interviewerName;
		this.respondentName = respondentName;
		this.awcCode =awcCode;
		this.audio =audio;
		this.sampleNum = samplenum;
		this.awcName =awcName;
		this.hscName = hscName;
		this.ward = ward;
		this.address =address;
		this.contactNum = contactNum;
		this.resultStatus = resultStatus;
		if(lastSubmission != null)this.lastSubmission = lastSubmission.toDate();
		if(startTime != null)this.startTime =startTime.toDate();
		this.duration =duration;
		this.urName = urName;
	}



	public RespondentData(List<Region> regions, List<District> districts,
			List<Block> blocks, List<Interviewer> interviewers) {
		
		this.regions =regions;
		this.districts =districts;
		this.blocks =blocks;
		this.interviewers = interviewers;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public String getHscName() {
		return hscName;
	}

	public List<Interviewer> getInterviewers() {
		return interviewers;
	}

	public void setInterviewers(List<Interviewer> interviewers) {
		this.interviewers = interviewers;
	}

	public String getUrName() {
		return urName;
	}



	public void setUrName(String urName) {
		this.urName = urName;
	}



	public String getHouseholdRel() {
		return householdRel;
	}



	public void setHouseholdRel(String householdRel) {
		this.householdRel = householdRel;
	}



	public void setHscName(String hscName) {
		this.hscName = hscName;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
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

	public String getResultStatus() {
		return resultStatus;
	}

	public String getAwcCode() {
		return awcCode;
	}

	public void setAwcCode(String awcCode) {
		this.awcCode = awcCode;
	}



	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAwcName() {
		return awcName;
	}

	public void setAwcName(String awcName) {
		this.awcName = awcName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
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

	public Date getLastSubmission() {
		return lastSubmission;
	}

	public void setLastSubmission(Date lastSubmission) {
		this.lastSubmission = lastSubmission;
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
	
	
	
}