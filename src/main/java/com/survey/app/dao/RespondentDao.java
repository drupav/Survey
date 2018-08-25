package com.survey.app.dao;

import com.survey.app.dto.RespondentData;
import com.survey.app.util.Page;

public interface RespondentDao {

Page<RespondentData> findAllRespondents(Long blockId, Long districtId,
		Long regionId, Long interviewerId, String searchString, Long pageNum, Long pageSize);
}
