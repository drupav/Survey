package com.survey.app.dto;

import java.util.List;

public class TreeViewData  {
	
 private List<AllSubmissionMetaData> items;
 private Long totalFilteredRecords;
 private  Long totalSamples;
 private  Long pendingSamples;
 
	public TreeViewData(List<AllSubmissionMetaData> items, Long totalFilteredRecords, Long totalSamples) {
		this.items = items;
		this.totalFilteredRecords = totalFilteredRecords;
		this.totalSamples = totalSamples;
	}

	public List<AllSubmissionMetaData> getItems() {
		return items;
	}

	public void setItems(List<AllSubmissionMetaData> items) {
		this.items = items;
	}

	public Long getTotalFilteredRecords() {
		return totalFilteredRecords;
	}

	public void setTotalFilteredRecords(Long totalFilteredRecords) {
		this.totalFilteredRecords = totalFilteredRecords;
	}

	public Long getTotalSamples() {
		return totalSamples;
	}

	public void setTotalSamples(Long totalSamples) {
		this.totalSamples = totalSamples;
	}

	public Long getPendingSamples() {
		return pendingSamples;
	}

	public void setPendingSamples(Long pendingSamples) {
		this.pendingSamples = pendingSamples;
	}

	
}
