package com.survey.app.service;

import java.util.List;

import com.survey.app.model.Block;
import com.survey.app.model.District;
import com.survey.app.model.Interviewer;
import com.survey.app.model.Region;

public interface SurveyCommonService {

	List<District> findAllDistricts();

	List<Block> findAllBlocks();

	List<Region> findAllRegions();
	
	List<Interviewer> findAllInterviewers();

	List<District> findDistrictsByRegionId(Long regionId);

	List<Block> findBlocksByDistrictId(Long regionId);
	

	List<Interviewer> findInterviewerById(Long interviewierId);

}
