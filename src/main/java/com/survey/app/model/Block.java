package com.survey.app.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.survey.app.entity.Entity;


@javax.persistence.Entity
@Table(name = "block")
public class Block implements Entity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "block_name", nullable = false)
    private String blockName;
    
    @Column(name = "block_code", nullable = false)
    private Long blockCode;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;
    
    @Column(name = "total_samples", nullable = false)
    private Long totalSamples;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	
	public Long getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(Long blockCode) {
		this.blockCode = blockCode;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Long getTotalSamples() {
		return totalSamples;
	}

	public void setTotalSamples(Long totalSamples) {
		this.totalSamples = totalSamples;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block choice = (Block) o;
        return Objects.equals(id, choice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
