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
import com.survey.app.model.Respondent;
import com.survey.app.model.UrCode;
import com.survey.app.model.Ward;
import com.survey.app.repository.AwcRepository;
import com.survey.app.repository.BlockRepository;
import com.survey.app.repository.DistrictRepository;
import com.survey.app.repository.UrCodeRepository;

@Component
public class Processor implements ItemProcessor<CSVContext,CSVContext> {
	
	@Autowired
	private AwcRepository  awcRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private BlockRepository blockRepository;
	
	@Autowired
	private UrCodeRepository urCodeRepository;

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
		respondent.setAwcCode(dto.getAwcCode());
		respondent.setAwcName(dto.getAwcName());
		if(dto.getUrCode() != null){
		UrCode urCode = urCodeRepository.findUrcodeByUrcode(dto.getUrCode());
		respondent.setUrCode(urCode);
		}
		
		if(dto.getBlockId() != null){
			Block block = blockRepository.findBlockByBlockCode(dto.getBlockId());
			respondent.setBlock(block);
		}
		respondent.setContactNum(dto.getContactNum());
		if(dto.getDistrictId() != null){
		District district = districtRepository.findDistrictsByDistricCode(dto.getDistrictId());
		respondent.setDistrict(district);
		}
		respondent.setInterviewer(dto.getInterviewerName());
		respondent.setHscName(dto.getHscName());
		respondent.setSampleNum(dto.getSampleNum());
		respondent.setVillageName(dto.getVillageName());
		respondent.setWard(dto.getWardId());
		respondent.setStartTime(dto.getStartTime());
		respondent.setDuration(dto.getDuration());
		return respondent;
	}

}