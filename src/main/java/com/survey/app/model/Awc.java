package com.survey.app.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.survey.app.entity.Entity;


@javax.persistence.Entity
@Table(name = "awc")
public class Awc implements Entity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "awc_code", nullable = false)
    private String awcCode;
    
    @Column(name = "awc_name", nullable = false)
    private String awcName;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Awc choice = (Awc) o;
        return Objects.equals(id, choice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
