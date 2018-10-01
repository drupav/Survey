package com.survey.app.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suervey.app.util.AppConstants;
import com.survey.app.job.dto.QualityCheckData;
import com.survey.app.service.QualityCheckService;
import com.survey.app.util.Page;


@RestController
@RequestMapping("/api/qualitycheck")
public class QualityCheckController {

@Autowired
private QualityCheckService qualityCheckService;


 @GetMapping
 public Page<QualityCheckData> getAllData( @RequestParam(value = "blockId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long blockId,
            									@RequestParam(value = "districtId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long districtId,
            									@RequestParam(value = "searchString", defaultValue = StringUtils.EMPTY) String searchString,
                                                @RequestParam(value = "offset", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Long page,
                                                @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Long size) {
    	
        return qualityCheckService.getAllQualityChecks(blockId, districtId, searchString, page, size);
    }
 
}
