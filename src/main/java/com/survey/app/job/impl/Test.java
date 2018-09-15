package com.survey.app.job.impl;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.survey.app.job.dto.RespondentDTO;
import com.survey.app.job.util.SurveyUtil;
import com.survey.app.model.Block;
import com.survey.app.model.District;
import com.survey.app.model.QualityCheck;
import com.survey.app.model.UrCode;

public class Test {
	 private static SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy HH:mm");
	 private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
public static void main(String[] args) throws IOException, ParseException {
	Reader in = new FileReader("F:\\Naveen_work\\Quality Check_csv.csv");
	Iterable<CSVRecord> records =SurveyUtil.getQualityCheckRecords(in);
	RespondentDTO dto = new RespondentDTO();
	records.iterator().next();
	for (CSVRecord record : records) {
		String startTime =record.get("starttime");System.out.println(Long.valueOf(record.get("QC1_1"))== 0?'N':'Y');
		QualityCheck qualityCheck = new QualityCheck();
		if(StringUtils.isNotEmpty(record.get("QC1_1")))qualityCheck.setAccompaniment(Long.valueOf(record.get("QC1_1"))== 0?'N':'Y');
		if(StringUtils.isNotEmpty(record.get("QC1_2")))qualityCheck.setSpotCheck(Long.valueOf(record.get("QC1_2"))== 0?'N':'Y');
		if(StringUtils.isNotEmpty(record.get("QC1_3")))qualityCheck.setBackCheck(Long.valueOf(record.get("QC1_3"))== 0?'N':'Y');
		if(StringUtils.isNotEmpty(record.get("QC1_4")))qualityCheck.setAudioCheck(Long.valueOf(record.get("QC1_4"))== 0?'N':'Y');
		if(StringUtils.isNotEmpty(record.get("starttime"))){
			try{
			qualityCheck.setStartTime(dateTimeFormat.parse(startTime));
			}catch (ParseException e) {
				qualityCheck.setStartTime(dateFormat.parse(startTime));
			}
		}
		if(StringUtils.isNotEmpty(record.get("endtime"))){
			try{
			qualityCheck.setEndTime(dateTimeFormat.parse(record.get("endtime")));
			}catch (ParseException e) {
				qualityCheck.setEndTime(dateFormat.parse(record.get("endtime")));
			}
		}
		if(StringUtils.isNotEmpty(record.get("collector_name")))qualityCheck.setInterviewerName(record.get("collector_name"));
		qualityCheck.setWardNo(record.get("Ward"));
		qualityCheck.setVillageName(record.get("Village"));
		qualityCheck.setWardName(record.get("Identification3_1"));
		if(StringUtils.isNotEmpty(record.get("Identification3_2")))qualityCheck.setAwcNo(Long.valueOf(record.get("Identification3_2")));
		qualityCheck.setAwcName(record.get("Identification3_3"));
		if(StringUtils.isNotEmpty(record.get("block"))){
		Block block = new Block();
		block.setId(Long.valueOf(record.get("block")));
		qualityCheck.setBlock(block);
		}
		if(StringUtils.isNotEmpty(record.get("district"))){
			District district = new District();
			district.setId(Long.valueOf(record.get("district")));
			qualityCheck.setDistrict(district);
		}
		if(StringUtils.isNotEmpty(record.get("UR"))){
			UrCode urCode = new UrCode();
			urCode.setId(Long.valueOf(record.get("UR")));
		}
	
	
	}
}
}
	