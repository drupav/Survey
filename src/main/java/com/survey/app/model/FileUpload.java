package com.survey.app.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.survey.app.entity.Entity;
import com.survey.app.model.audit.UserDateAudit;



@javax.persistence.Entity
@Table(name = "file_upload")
public class FileUpload extends UserDateAudit implements Entity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;
    
    @Column(name = "file_location", nullable = false)
    private String fileLocation;

    @Column(name = "upload_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date uploadDate;
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileUpload choice = (FileUpload) o;
        return Objects.equals(id, choice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
