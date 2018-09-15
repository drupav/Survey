package com.survey.app.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.survey.app.entity.Entity;


@javax.persistence.Entity
@Table(name = "ur_code")
public class UrCode implements Entity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ur_code", nullable = false)
    private Long uCode;
    
    @Column(name = "ur_name", nullable = false)
    private String urName;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Long getUCode() {
		return uCode;
	}

	public void setUrCode(Long uCode) {
		this.uCode = uCode;
	}

	public String getUrName() {
		return urName;
	}

	public void setUrName(String urName) {
		this.urName = urName;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrCode choice = (UrCode) o;
        return Objects.equals(id, choice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
