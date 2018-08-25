package com.survey.app.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.survey.app.dto.RespondentData;
import com.survey.app.util.Page;

public interface RespondentService {

Page<RespondentData> getAllRespondents(Long blockId, Long districtId,
		Long regionId, Long interviewerId, String searchString, Long pageNum, Long pageSize);

Resource loadFileAsResource(Long fileId);

	
}
