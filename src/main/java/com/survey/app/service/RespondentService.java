package com.survey.app.service;

import org.springframework.core.io.Resource;

import com.survey.app.dto.InterviewerData;
import com.survey.app.dto.RespondentData;
import com.survey.app.dto.TreeViewData;
import com.survey.app.util.Page;

public interface RespondentService {

Page<RespondentData> getAllRespondents(Long blockId, Long districtId,
		Long regionId, Long interviewerId, String searchString, Long pageNum, Long pageSize);

Resource loadFileAsResource(Long fileId);

TreeViewData getMetaData(Long page, Long size, Long blockId, Long districtId);

Page<InterviewerData> getInterviewerData(Long blockId, Long districtId, String searchString, Long page, Long size);

Page<InterviewerData> getinterviewierQualitycheckData(Long blockId, Long districtId, String searchString, Long page,Long size);

	
}
