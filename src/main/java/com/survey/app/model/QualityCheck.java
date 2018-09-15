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
@Table(name = "quality_check")
public class QualityCheck  extends UserDateAudit implements Entity {
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
    
    @JoinColumn(name = "interviewer_name", nullable = false)
    private String interviewerName;
    
    @ManyToOne
    @JoinColumn(name = "ur_id", nullable = false)
    private UrCode urCode;

    @Column(name = "village_name", nullable = false)
    private String villageName;
    
    @Column(name = "ward_no", nullable = false)
    private String wardNo;
    
    @Column(name = "ward_name", nullable = false)
    private String wardName;
    
    @Column(name = "awc_no", nullable = false)
    private Long awcNo;
    
    @Column(name = "awc_name", nullable = false)
    private String awcName;

    
    @Column(name = "accompaniment", nullable = false)
    private char accompaniment='N';
    
    @Column(name = "spot_check", nullable = false)
    private char spotCheck='N';
    
    @Column(name = "back_check", nullable = false)
    private char backCheck='N';
    
    @Column(name = "audio_check", nullable = false)
    private char audioCheck='N';
    
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

	public UrCode getUrCode() {
		return urCode;
	}

	public void setUrCode(UrCode urCode) {
		this.urCode = urCode;
	}

	public Long getAwcNo() {
		return awcNo;
	}

	public void setAwcNo(Long awcNo) {
		this.awcNo = awcNo;
	}

	public String getAwcName() {
		return awcName;
	}

	public void setAwcName(String awcName) {
		this.awcName = awcName;
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

	public String getWardNo() {
		return wardNo;
	}

	public void setWardNo(String wardNo) {
		this.wardNo = wardNo;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public char getAccompaniment() {
		return accompaniment;
	}

	public void setAccompaniment(char accompaniment) {
		this.accompaniment = accompaniment;
	}

	public char getSpotCheck() {
		return spotCheck;
	}

	public void setSpotCheck(char spotCheck) {
		this.spotCheck = spotCheck;
	}

	public char getBackCheck() {
		return backCheck;
	}

	public void setBackCheck(char backCheck) {
		this.backCheck = backCheck;
	}

	public char getAudioCheck() {
		return audioCheck;
	}

	public void setAudioCheck(char audioCheck) {
		this.audioCheck = audioCheck;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualityCheck choice = (QualityCheck) o;
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
    
    

}
