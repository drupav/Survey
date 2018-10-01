package com.survey.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suervey.app.util.AppConstants;
import com.survey.app.dto.AllSubmissionMetaData;
import com.survey.app.dto.InterviewerData;
import com.survey.app.dto.RespondentData;
import com.survey.app.dto.TreeViewData;
import com.survey.app.model.Block;
import com.survey.app.model.District;
import com.survey.app.model.Interviewer;
import com.survey.app.model.Region;
import com.survey.app.service.RespondentService;
import com.survey.app.service.SurveyCommonService;
import com.survey.app.util.Page;


@RestController
@RequestMapping("/api/respondents")
public class RespondentController {

@Autowired
private RespondentService respondentService;

@Autowired
private SurveyCommonService surveyCommonService;


 @GetMapping
 public Page<RespondentData> getPolls( @RequestParam(value = "blockId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long blockId,
            									@RequestParam(value = "districtId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long districtId,
            									@RequestParam(value = "regionId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long regionId,
            									@RequestParam(value = "searchString", defaultValue = StringUtils.EMPTY) String searchString,
            									@RequestParam(value = "interviewerId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long interviewerId,
                                                @RequestParam(value = "offset", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Long page,
                                                @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Long size) {
    	
        return respondentService.getAllRespondents(blockId, districtId, regionId,interviewerId, searchString, page, size);
    }
 
    @GetMapping("/template")
	public RespondentData getRespondentTemplateData( @RequestParam(value = "regionId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long regionId,
			 @RequestParam(value = "districtId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long districtId,
			 	@RequestParam(value = "interviewerId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long interviewerId)
	{
    	List<Region> regions = this.surveyCommonService.findAllRegions();
    	List<District> districts =regionId == 0?this.surveyCommonService.findAllDistricts():this.surveyCommonService.findDistrictsByRegionId(regionId);
    	List<Block> blocks = districtId==0?this.surveyCommonService.findAllBlocks():this.surveyCommonService.findBlocksByDistrictId(districtId);
    	List<Interviewer> interviewers = districtId==0?this.surveyCommonService.findAllInterviewers():this.surveyCommonService.findInterviewerById(interviewerId);
		return new RespondentData(regions,districts,blocks,interviewers);
    	
	}
    @GetMapping("/getMetData")
    public TreeViewData getMetaData(@RequestParam(value = "offset", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Long page,
                                                   @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Long size,
                                                   @RequestParam(value = "blockId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long blockId,
               									@RequestParam(value = "districtId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long districtId) {
       	
           return respondentService.getMetaData(page, size,blockId,districtId);
       }
    
    @GetMapping("/getinterviewierData")
    public Page<InterviewerData> getInterviewerData(@RequestParam(value = "offset", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Long page,
                                                   @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Long size,
                                                   @RequestParam(value = "searchString", defaultValue = StringUtils.EMPTY) String searchString,
                                                   @RequestParam(value = "blockId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long blockId,
               									@RequestParam(value = "districtId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long districtId) {
       	
           return respondentService.getInterviewerData(blockId, districtId, searchString, page, size);
    }
    
    @GetMapping("/getinterviewierQualitycheck")
    public Page<InterviewerData> getinterviewierQualitycheck(@RequestParam(value = "offset", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Long page,
                                                   @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Long size,
                                                   @RequestParam(value = "searchString", defaultValue = StringUtils.EMPTY) String searchString,
                                                   @RequestParam(value = "blockId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long blockId,
               									@RequestParam(value = "districtId", defaultValue = AppConstants.DEFAULT_LONG_VALUE) Long districtId) {
       	
           return respondentService.getinterviewierQualitycheckData(blockId, districtId, searchString, page, size);
    }
    
    @GetMapping("/downloadaudiofile/{respondentId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long respondentId, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = respondentService.loadFileAsResource(respondentId);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
