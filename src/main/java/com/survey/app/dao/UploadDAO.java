package com.survey.app.dao;

import com.survey.app.dto.UploadData;
import com.survey.app.util.Page;

public interface UploadDAO {

	Page<UploadData> findAllUploads(Long pageNum, Long pageSize);
}
