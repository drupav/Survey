
package com.survey.app.job.impl;

import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
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

@Component
public class ReaderCsv implements ItemReader<CSVContext> {
private JobParameters parameters;
	
private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm",Locale.ENGLISH);
private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a",Locale.ENGLISH);
	 private Boolean isFirst = true;
	 
   @BeforeStep
   public void beforeStep(final StepExecution stepExecution) {
	 parameters = stepExecution.getJobExecution().getJobParameters();
   }
	 
	@Override
	public CSVContext read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		CSVContext context = new CSVContext();
		
		
		boolean firstRow = false;
		 ArrayList<RespondentDTO>  list = new ArrayList();
		
		if(isFirst){
			String filelocation =parameters.getString("filelocation");
			Reader in = new FileReader(filelocation);
			 CSVParser records =new CSVParser(in, CSVFormat.DEFAULT);
		try{
			for (CSVRecord record : records.getRecords()) {
				if(!firstRow){
					firstRow = true;
				}else{
				list.add(convertDTO(record));
				}
			}
		}finally{
			records.close();
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
		if(StringUtils.isNotEmpty(record.get(0)))dto.setSerialNumber(Long.valueOf(record.get(0)));
		String sub =record.get(1);
		if(StringUtils.isNotEmpty(record.get(1))){
			try{
			dto.setSubmissionDate(dateTimeFormat.parse(sub));
			}catch (java.text.ParseException e) {
				dto.setSubmissionDate(dateFormat.parse(sub));
			}
		}
		if(StringUtils.isNotEmpty(record.get(2))){
			try{
			  dto.setStartTime(dateTimeFormat.parse(record.get(2)));
			}catch (java.text.ParseException e) {
				dto.setStartTime(dateFormat.parse(record.get(2)));
			}
		}
		String endTime =record.get(3);
		if(StringUtils.isNotEmpty(record.get(3))){
			try{
			dto.setEndTime(dateTimeFormat.parse(endTime));
			}catch (java.text.ParseException e) {
				dto.setEndTime(dateFormat.parse(endTime));
			}
		}
		if(StringUtils.isNotEmpty(record.get(9)))dto.setDuration(Long.valueOf(record.get(9)));
		dto.setAudio(record.get(13));
		if(StringUtils.isNotEmpty(record.get(16)))dto.setDistrictId(Long.valueOf(record.get(16)));
		if(StringUtils.isNotEmpty(record.get(17)))dto.setBlockId(Long.valueOf(record.get(17)));
		//dto.setHscName(record.get("Identification2_2"));
		dto.setVillageName(record.get(21));
		if(StringUtils.isNotEmpty(record.get(18)))dto.setUrCode(Long.valueOf(record.get(18)));
		dto.setAwcCode(record.get(24));
		dto.setAwcName(record.get(23));
		dto.setRespondentName(record.get(27));
		dto.setHouseholdRel(record.get(28));
		dto.setInterviewerName(record.get(14));
		dto.setInterviewerCode(record.get(15));
		if(StringUtils.isNotEmpty(record.get(32)))dto.setSampleNum(Long.valueOf(record.get(32)));
		if(StringUtils.isNotEmpty(record.get(1763)))dto.setResult(record.get(1763));
		if(StringUtils.isNotEmpty(record.get(22)))dto.setWardId(Long.valueOf(record.get(22)));
		return dto;
	}

}