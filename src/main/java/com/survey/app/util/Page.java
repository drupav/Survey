/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.survey.app.util;

import java.util.List;

public class Page<E> {

    private final int totalFilteredRecords;
    private final List<E> pageItems;
    private final Integer completeSamples;
    private final Long totalSamples;
    private final Long pendingSamples;
	private Long totalInterviews;

    public Page(final List<E> pageItems, final Integer totalFilteredRecords, Integer completeSamples, Long totalSamples) {
        this.pageItems = pageItems;
        this.totalFilteredRecords = totalFilteredRecords;
        this.completeSamples = completeSamples;
        this.totalSamples = totalSamples;
        this.pendingSamples = totalSamples-Long.valueOf(totalFilteredRecords) ;
        
    }

    public int getTotalFilteredRecords() {
        return this.totalFilteredRecords;
    }

    public List<E> getPageItems() {
        return pageItems;
    }

	public Integer getCompleteSamples() {
		return completeSamples;
	}

	public Long getTotalSamples() {
		return totalSamples;
	}

	public Long getPendingSamples() {
		return pendingSamples;
	}

	public void setTotalInterviews(Long totalInterviews) {
		this.totalInterviews =totalInterviews;
		
	}

	public Long getTotalInterviews() {
		return totalInterviews;
	}
    
    
}