package com.survey.app.dao;

import com.survey.app.dto.InterviewerData;
import com.survey.app.dto.RespondentData;
import com.survey.app.dto.TreeViewData;
import com.survey.app.util.Page;

public interface RespondentDao {

Page<RespondentData> findAllRespondents(Long blockId, Long districtId,
		Long regionId, Long interviewerId, String searchString, Long pageNum, Long pageSize);

TreeViewData getMetaData(Long page, Long size, Long blockId, Long districtId);

Page<InterviewerData> getInterviewerData(Long blockId, Long districtId, String searchString, Long page, Long size);

Page<InterviewerData> getinterviewierQualitycheckData(Long blockId, Long districtId, String searchString, Long page,
		Long size);
}
