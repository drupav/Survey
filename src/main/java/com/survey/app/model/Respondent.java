package com.survey.app.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.survey.app.entity.Entity;
import com.survey.app.model.audit.UserDateAudit;


@javax.persistence.Entity
@Table(name = "respondent")
public class Respondent  extends UserDateAudit implements Entity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8589165463723014137L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;
    
    @ManyToOne
    @JoinColumn(name = "block_id", nullable = false)
    private Block block;
    
  //  @ManyToOne
   // @JoinColumn(name = "interviewer_id", nullable = false)
    @Column(name = "interviewer_name", nullable = false)
    private String interviewer;
    
    @Column(name = "awc_code", nullable = false)
    private String awcCode;
    
    @Column(name = "awc_name", nullable = false)
    private String awcName;
    
    @ManyToOne
    @JoinColumn(name = "ur_id", nullable = false)
    private UrCode urCode;

    @Column(name = "village_name", nullable = false)
    private String villageName;
    
    @Column(name = "respondent_name", nullable = false)
    private String respondentName;
    
    @Column(name = "serial_num", nullable = false)
    private Long serialNum;
    
    @Column(name = "sample_num", nullable = false)
    private Long sampleNum;
    
    @Column(name = "hsc_name", nullable = false)
    private String hscName;

    @Column(name = "ward_id", nullable = false)
    private Long ward;
    
    @Column(name = "address", nullable = false)
    private String address;
    
    @Column(name = "contact_num", nullable = false)
    private String contactNum;
    
    @Column(name = "result_status", nullable = false)
    private String resultStatus;
    
    @Column(name = "audio", nullable = false)
    private String audio;
    
    @Column(name = "interviewer_code", nullable = false)
    private String interviewerCode;
    
    @Column(name = "household_rel", nullable = false)
    private String householdRel;
    
    @Column(name = "submission_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date submissionDate;
    
    @Column(name = "duration", nullable = false)
    private Long duration;
    
    @Column(name = "start_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    
    @Column(name = "end_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
   

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public String getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}
	

	public String getRespondentName() {
		return respondentName;
	}

	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
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

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	
	public String getAwcCode() {
		return awcCode;
	}

	public void setAwcCode(String awcCode) {
		this.awcCode = awcCode;
	}

	public String getAwcName() {
		return awcName;
	}

	public void setAwcName(String awcName) {
		this.awcName = awcName;
	}

	public String getHscName() {
		return hscName;
	}

	public void setHscName(String hscName) {
		this.hscName = hscName;
	}

	public Long getWard() {
		return ward;
	}

	public void setWard(Long ward) {
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

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Respondent choice = (Respondent) o;
        return Objects.equals(id, choice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
    
	public UrCode getUrCode() {
		return urCode;
	}

	public void setUrCode(UrCode urCode) {
		this.urCode = urCode;
	}

	public Long getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}

	public String getInterviewerCode() {
		return interviewerCode;
	}

	public void setInterviewerCode(String interviewerCode) {
		this.interviewerCode = interviewerCode;
	}

	public String getHouseholdRel() {
		return householdRel;
	}

	public void setHouseholdRel(String householdRel) {
		this.householdRel = householdRel;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
