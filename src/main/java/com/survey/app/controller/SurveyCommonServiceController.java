package com.survey.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.app.model.Block;
import com.survey.app.model.District;
import com.survey.app.service.SurveyCommonService;


@RestController
@RequestMapping("/api/commonservice")
public class SurveyCommonServiceController {

@Autowired
private SurveyCommonService surveyCommonService;


    @GetMapping("/getdistrics/{regionId}")
	public ResponseEntity<List<District>> getRespondentTemplateData(@PathVariable("regionId") final Long regionId)
	{
    	return new  ResponseEntity<>(this.surveyCommonService.findDistrictsByRegionId(regionId), HttpStatus.OK);
    	
	}
    
    @GetMapping("/getblocks/{districtId}")
   	public ResponseEntity<List<Block>> getblocks(@PathVariable("districtId") final Long districtId)
   	{
    	return new  ResponseEntity<>(this.surveyCommonService.findBlocksByDistrictId(districtId), HttpStatus.OK);
       	
   	}

}
