package com.survey.app.job.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.survey.app.config.CSVContext;
import com.survey.app.job.dto.RespondentDTO;
import com.survey.app.model.Awc;
import com.survey.app.model.Block;
import com.survey.app.model.District;
import com.survey.app.model.Interviewer;
import com.survey.app.model.Respondent;
import com.survey.app.model.Ward;
import com.survey.app.repository.AwcRepository;

@Component
public class Processor implements ItemProcessor<CSVContext,CSVContext> {
	
	@Autowired
	private AwcRepository  awcRepository;

	@Override
	public CSVContext process(CSVContext data) throws Exception {
		List<Respondent> respondents =data.getRespondentDTOs().stream().map(DTO -> convertEntity(DTO)).collect(Collectors.toList());
		data.setRespondentDatas(respondents);
		return data;
	}

	private Respondent convertEntity(RespondentDTO dto) {
		
		Respondent respondent = new Respondent();
		respondent.setSubmissionDate(dto.getSubmissionDate());
		respondent.setAddress(dto.getAddress());
		respondent.setAudio(dto.getAudio());
		respondent.setRespondentName(dto.getRespondentName());
		Awc awc =awcRepository.findAwcByAwcCode(dto.getAwcCode());
		respondent.setAwc(awc);
		if(dto.getBlockId() != null){
		Block block = new Block();
		block.setId(dto.getBlockId());
		respondent.setBlock(block);
		}
		respondent.setContactNum(dto.getContactNum());
		if(dto.getDistrictId() != null){
		District district = new District();
		district.setId(dto.getDistrictId());
		respondent.setDistrict(district);
		}
		if(dto.getInterviewerId() != null){
			Interviewer interviewer = new Interviewer();
			interviewer.setId(dto.getInterviewerId());
			respondent.setInterviewer(interviewer);
		}
		respondent.setHscName(dto.getHscName());
		respondent.setSampleNum(dto.getSampleNum());
		respondent.setVillageName(dto.getVillageName());
		if(dto.getWardId() != null){
		Ward ward = new Ward();
		ward.setId(dto.getWardId());
		respondent.setWard(ward);
		}
		respondent.setStartTime(dto.getStartTime());
		respondent.setDuration(dto.getDuration());
		return respondent;
	}

}