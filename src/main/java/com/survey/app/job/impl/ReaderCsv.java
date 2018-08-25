
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
	
	 private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
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
		String startTime =record.get("starttime");
		if(StringUtils.isNotEmpty(record.get("Block")))dto.setSubmissionDate(dateFormat.parse(sub));
		if(StringUtils.isNotEmpty(record.get("starttime")))dto.setStartTime(dateFormat.parse(startTime));
		if(StringUtils.isNotEmpty(record.get("starttime")))dto.setStartTime(dateFormat.parse(record.get("starttime")));
		if(StringUtils.isNotEmpty(record.get("duration")))dto.setDuration(Long.valueOf(record.get("duration")));
		dto.setAudio(record.get("audio1"));
		if(StringUtils.isNotEmpty(record.get("District")))dto.setDistrictId(Long.valueOf(record.get("District")));
		if(StringUtils.isNotEmpty(record.get("Block")))dto.setBlockId(Long.valueOf(record.get("Block")));
		dto.setHscName(record.get("Identification2_1"));
		dto.setVillageName(record.get("Village"));
		dto.setAwcCode(record.get("Identification3_2"));
		dto.setRespondentName(record.get("Identification3_1"));
		dto.setAddress(record.get("Identification4_1"));
		if(StringUtils.isNotEmpty(record.get("Identification2_1")))dto.setInterviewerId(Long.valueOf(record.get("Identification2_1")));
		dto.setContactNum(record.get("Identification4_2"));
		if(StringUtils.isNotEmpty(record.get("duration")))dto.setDuration(Long.valueOf(record.get("duration")));
		if(StringUtils.isNotEmpty(record.get("Identification4_4")))dto.setSampleNum(Long.valueOf(record.get("Identification4_4")));
		if(StringUtils.isNotEmpty(record.get("ward")))dto.setWardId(Long.valueOf(record.get("ward")));
		return dto;
	}

}