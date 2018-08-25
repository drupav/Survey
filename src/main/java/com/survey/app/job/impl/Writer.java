package com.survey.app.job.impl;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.survey.app.config.CSVContext;
import com.survey.app.repository.RespondentRepository;

@Component
public class Writer implements ItemWriter<CSVContext> {

	@Autowired
	private RespondentRepository respondentRepository;
	@Override
	public void write(List<? extends CSVContext> messages) throws Exception {
		for (CSVContext msg : messages) {
			
			respondentRepository.saveAll(msg.getRespondentDatas());
		}
	}

}