package com.survey.app.job.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.survey.app.config.CSVContext;
import com.survey.app.job.dto.RespondentDTO;
import com.survey.app.model.Block;
import com.survey.app.model.District;
import com.survey.app.model.Respondent;
import com.survey.app.model.UrCode;
import com.survey.app.repository.BlockRepository;
import com.survey.app.repository.DistrictRepository;
import com.survey.app.repository.UrCodeRepository;

@Component
public class Processor implements ItemProcessor<CSVContext,CSVContext> {
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private BlockRepository blockRepository;
	
	@Autowired
	private UrCodeRepository urCodeRepository;
	
	Map<Long, Block> blockMap = new HashMap<>();
	Map<Long, District> districtMap = new HashMap<>();
	Map<Long, UrCode> urMap = new HashMap<>();

	@Override
	public CSVContext process(CSVContext data) throws Exception {
		blockMap = blockRepository.findAll().stream().collect(Collectors.toMap(Block::getBlockCode,
                Function.identity()));  
		districtMap = districtRepository.findAll().stream().collect(Collectors.toMap(District::getDistrictCode,
                Function.identity()));  
		urMap = urCodeRepository.findAll().stream().collect(Collectors.toMap(UrCode::getUCode,
                Function.identity()));  
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
		if(StringUtils.isNotEmpty(dto.getResult()))
		respondent.setResultStatus(Long.valueOf(dto.getResult()) == 1?"Completed":"Pending");
		if(dto.getUrCode() != null && urMap.containsKey(dto.getUrCode())){
		//UrCode urCode = urCodeRepository.findUrcodeByUrcode(dto.getUrCode());
		if(urMap.containsKey(dto.getUrCode()))respondent.setUrCode(urMap.get(dto.getUrCode()));
		}
		
		if(dto.getBlockId() != null && blockMap.containsKey(dto.getBlockId())){
			//Block block = blockRepository.findBlockByBlockCode(dto.getBlockId());
			respondent.setBlock(blockMap.get(dto.getBlockId()));
		}
		respondent.setContactNum(dto.getContactNum());
		if(dto.getDistrictId() != null && districtMap.containsKey(dto.getDistrictId())){
		//District district = districtRepository.findDistrictsByDistricCode(dto.getDistrictId());
		respondent.setDistrict(districtMap.get(dto.getDistrictId()));
		}
		respondent.setInterviewer(dto.getInterviewerName());
		respondent.setHscName(dto.getHscName());
		respondent.setSampleNum(dto.getSampleNum());
		respondent.setVillageName(dto.getVillageName());
		respondent.setWard(dto.getWardId());
		respondent.setStartTime(dto.getStartTime());
		if(dto.getDuration() != null)respondent.setDuration(TimeUnit.SECONDS.toMinutes(dto.getDuration()));
		respondent.setSerialNum(dto.getSerialNumber());
		respondent.setEndTime(dto.getEndTime());
		respondent.setInterviewerCode(dto.getInterviewerCode());
		respondent.setHouseholdRel(dto.getHouseholdRel());
		return respondent;
	}

}