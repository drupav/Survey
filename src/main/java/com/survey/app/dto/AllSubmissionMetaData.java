package com.survey.app.dto;

import java.util.Date;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AllSubmissionMetaData {
	private Long id;
	private Long districtId;
	private String districtName;
	private String blockName;
	private Long completedSamples;
	private Long peningSamples;
	private String completePercent;
	private String pendingPercent;
	private Long totalSamples;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date  lastSubmission;
	
	public AllSubmissionMetaData(Long id, Long districtId, String districtName, String blockName, Long completed,
			Long pending, Double completePercent, Double pedingpercent, Long totalSamples, LocalDate lastSubmission) {
		this.id = id;
		this.districtId = districtId == 0 ?null:districtId;
		this.districtName = districtName;
		this.blockName = blockName;
		this.completedSamples = completed;
		this.peningSamples = pending;
		this.completePercent = completePercent+" %";
		this.pendingPercent = pedingpercent+" %";
		this.totalSamples = totalSamples;
		if(lastSubmission != null)this.lastSubmission = lastSubmission.toDate();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
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
	public Long getCompletedSamples() {
		return completedSamples;
	}
	public void setCompletedSamples(Long completedSamples) {
		this.completedSamples = completedSamples;
	}
	public Long getPeningSamples() {
		return peningSamples;
	}
	public void setPeningSamples(Long peningSamples) {
		this.peningSamples = peningSamples;
	}
	public String getCompletePercent() {
		return completePercent;
	}
	public void setCompletePercent(String completePercent) {
		this.completePercent = completePercent;
	}
	public String getPendingPercent() {
		return pendingPercent;
	}
	public void setPendingPercent(String pendingPercent) {
		this.pendingPercent = pendingPercent;
	}

	public Long getTotalSamples() {
		return totalSamples;
	}

	public void setTotalSamples(Long totalSamples) {
		this.totalSamples = totalSamples;
	}

	public Date getLastSubmission() {
		return lastSubmission;
	}

	public void setLastSubmission(Date lastSubmission) {
		this.lastSubmission = lastSubmission;
	}
	
}
