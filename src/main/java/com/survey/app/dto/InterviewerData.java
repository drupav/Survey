package com.survey.app.dto;

public class InterviewerData {

	private Long id;
	private String districtName;
	private String blockName;
	private String interviewerCode;
	private String interviewerName;
	private Long completedSamples;
	private Long accompaniments;
	private Long spotChecks;
	private Long backChecks;
	private Long audioChecks;
	
	public InterviewerData(Long id, Long districtId, String districtName, String blockName, String interviewerCode,
			String interviewerName, Long noOfsamples) {
		  this.id = id;
		  this.districtName = districtName;
		  this.blockName = blockName;
		  this.interviewerCode = interviewerCode;
		  this.interviewerName = interviewerName;
		  this.completedSamples = noOfsamples;
	}
	
	public InterviewerData(Long id, String districtName, String blockName, String interviewerName,Long accompaniments, Long spotChecks, 
			  Long backChecks, Long audioChecks) {
		this.id = id;
		this.districtName =districtName;
		this.blockName = blockName;
		this.interviewerName = interviewerName;
		this.accompaniments =accompaniments;
		this.spotChecks = spotChecks;
		this.backChecks =backChecks;
		this.audioChecks = audioChecks;
	}

	public InterviewerData(String interviewCode, String interviewerName, Long totalSamples) {
		this.interviewerCode = interviewCode;
		this.interviewerName = interviewerName;
		this.completedSamples  = totalSamples;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getInterviewerCode() {
		return interviewerCode;
	}
	public void setInterviewerCode(String interviewerCode) {
		this.interviewerCode = interviewerCode;
	}
	public String getInterviewerName() {
		return interviewerName;
	}
	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}
	public Long getCompletedSamples() {
		return completedSamples;
	}
	public void setCompletedSamples(Long completedSamples) {
		this.completedSamples = completedSamples;
	}

	public Long getAccompaniments() {
		return accompaniments;
	}

	public void setAccompaniments(Long accompaniments) {
		this.accompaniments = accompaniments;
	}

	public Long getSpotChecks() {
		return spotChecks;
	}

	public void setSpotChecks(Long spotChecks) {
		this.spotChecks = spotChecks;
	}

	public Long getBackChecks() {
		return backChecks;
	}

	public void setBackChecks(Long backChecks) {
		this.backChecks = backChecks;
	}

	public Long getAudioChecks() {
		return audioChecks;
	}

	public void setAudioChecks(Long audioChecks) {
		this.audioChecks = audioChecks;
	}
	
}
