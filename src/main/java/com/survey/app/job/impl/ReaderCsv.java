
package com.survey.app.job.impl;

import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.survey.app.config.CSVContext;
import com.survey.app.job.dto.RespondentDTO;
import com.survey.app.job.util.SurveyUtil;

@Component
public class ReaderCsv implements ItemReader<CSVContext> {
private JobParameters parameters;
	
private static SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy HH:mm");
private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
	 private Boolean isFirst = true;
	 
   @BeforeStep
   public void beforeStep(final StepExecution stepExecution) {
	 parameters = stepExecution.getJobExecution().getJobParameters();
   }
	 
	@Override
	public CSVContext read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		boolean firstRow = false;
		 ArrayList<RespondentDTO>  list = new ArrayList();
		CSVContext context = new CSVContext();
		if(isFirst){
			String filelocation =parameters.getString("filelocation");
			Reader in = new FileReader(filelocation);
			Iterable<CSVRecord> records =SurveyUtil.getrecords(in);
			
			for (CSVRecord record : records) {
				if(!firstRow){
					firstRow = true;
				}else{
				list.add(convertDTO(record));
				}
			}
			context.setRespondentDTOs(list);
			
		}
		if(!isFirst){isFirst = true;return null;}
		isFirst = false;
		//else list.clear();
        return context;
	}//

	private RespondentDTO convertDTO(CSVRecord record) throws java.text.ParseException {
		RespondentDTO dto = new RespondentDTO();
		String sub =record.get("SubmissionDate");
		if(StringUtils.isNotEmpty(record.get("SubmissionDate"))){
			try{
			dto.setSubmissionDate(dateTimeFormat.parse(sub));
			}catch (java.text.ParseException e) {
				dto.setSubmissionDate(dateFormat.parse(sub));
			}
		}
		if(StringUtils.isNotEmpty(record.get("Starttime"))){
			try{
			  dto.setStartTime(dateTimeFormat.parse(record.get("Starttime")));
			}catch (java.text.ParseException e) {
				dto.setStartTime(dateFormat.parse(record.get("Starttime")));
			}
		}
		if(StringUtils.isNotEmpty(record.get("Duration")))dto.setDuration(Long.valueOf(record.get("Duration")));
		dto.setAudio(record.get("Audio1"));
		if(StringUtils.isNotEmpty(record.get("District")))dto.setDistrictId(Long.valueOf(record.get("District")));
		if(StringUtils.isNotEmpty(record.get("Block")))dto.setBlockId(Long.valueOf(record.get("Block")));
		dto.setHscName(record.get("Identification2_2"));
		dto.setVillageName(record.get("Village"));
		if(StringUtils.isNotEmpty(record.get("UR")))dto.setUrCode(Long.valueOf(record.get("UR")));
		dto.setAwcCode(record.get("awc"));
		dto.setAwcName(record.get("Identification3_1"));
		dto.setRespondentName(record.get("Identification3_3a"));
		dto.setAddress(record.get("Identification4_1"));
		dto.setInterviewerName(record.get("Collector_name"));
		//if(StringUtils.isNotEmpty(record.get("Identification4_4")))dto.setSampleNum(Long.valueOf(record.get("Identification4_4")));
		if(StringUtils.isNotEmpty(record.get("Ward")))dto.setWardId(Long.valueOf(record.get("Ward")));
		return dto;
	}

}