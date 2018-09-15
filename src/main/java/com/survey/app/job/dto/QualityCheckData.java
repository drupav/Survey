package com.survey.app.job.dto;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QualityCheckData {
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:MM")
	private Date startTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:MM")
	private Date endTime;
	private String interviewerName;
    private Long urId;
    private String villageName;
	private String awcName;
	private String accompaniment;
	private String districtName;
	private String blockName;
	private String urname;
	private String spotcheck;
	private String backcheck;
	private String audiocheck;
	private String wardName;
	
	public QualityCheckData(Long id, String districtName, String blockName, String villageName,
			String interviewerName, String urname, String wardName, String awcName, DateTime startTime,
			DateTime endTime, String accompaniment, String spotcheck, String backcheck, String audiocheck) {
		this.id = id;
		this.districtName = districtName;
		this.blockName =blockName;
		this.villageName =villageName;
		this.interviewerName = interviewerName;
		this.urname = urname;
		this.awcName = awcName;
		this.wardName = wardName;
		if(startTime != null)this.startTime = startTime.toDate();
		if(endTime != null) this.endTime =endTime.toDate();
		this.accompaniment = accompaniment;
		this.spotcheck = spotcheck;
		this.backcheck = backcheck;
		this.audiocheck = audiocheck;
				
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public Long getUrId() {
		return urId;
	}

	public void setUrId(Long urId) {
		this.urId = urId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getAwcName() {
		return awcName;
	}

	public void setAwcName(String awcName) {
		this.awcName = awcName;
	}

	public String getAccompaniment() {
		return accompaniment;
	}

	public void setAccompaniment(String accompaniment) {
		this.accompaniment = accompaniment;
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

	public String getUrname() {
		return urname;
	}

	public void setUrname(String urname) {
		this.urname = urname;
	}

	public String getSpotcheck() {
		return spotcheck;
	}

	public void setSpotcheck(String spotcheck) {
		this.spotcheck = spotcheck;
	}

	public String getBackcheck() {
		return backcheck;
	}

	public void setBackcheck(String backcheck) {
		this.backcheck = backcheck;
	}

	public String getAudiocheck() {
		return audiocheck;
	}

	public void setAudiocheck(String audiocheck) {
		this.audiocheck = audiocheck;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
		



	
}