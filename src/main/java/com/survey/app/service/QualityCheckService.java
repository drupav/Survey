package com.survey.app.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import com.survey.app.job.dto.QualityCheckData;
import com.survey.app.util.Page;

public interface QualityCheckService {
	
public void processQualityCheckRecord(String filePath) throws FileNotFoundException, IOException, ParseException;

Page<QualityCheckData> getAllQualityChecks(Long blockId, Long districtId, String searchString, Long pageNum,
		Long pageSize);
}
