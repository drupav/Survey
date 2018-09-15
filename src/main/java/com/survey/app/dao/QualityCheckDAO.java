package com.survey.app.dao;

import com.survey.app.job.dto.QualityCheckData;
import com.survey.app.util.Page;

public interface QualityCheckDAO {

Page<QualityCheckData> findAllData(Long blockId, Long districtId, String searchString, Long pageNum, Long pageSize);
}
