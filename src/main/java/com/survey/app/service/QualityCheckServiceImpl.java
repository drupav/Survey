package com.survey.app.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.app.dao.QualityCheckDAO;
import com.survey.app.job.dto.QualityCheckData;
import com.survey.app.job.util.SurveyUtil;
import com.survey.app.model.Block;
import com.survey.app.model.District;
import com.survey.app.model.QualityCheck;
import com.survey.app.model.UrCode;
import com.survey.app.repository.BlockRepository;
import com.survey.app.repository.DistrictRepository;
import com.survey.app.repository.QualityCheckRepository;
import com.survey.app.repository.UrCodeRepository;
import com.survey.app.util.Page;

@Service
public class QualityCheckServiceImpl implements QualityCheckService{
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm",Locale.ENGLISH);
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a",Locale.ENGLISH);
	 
	@Autowired
	private QualityCheckRepository qualityCheckRepository;
	
	@Autowired
	private BlockRepository blockRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private QualityCheckDAO aualityCheckDAO;
	
	@Autowired
	private UrCodeRepository urCodeRepository;
	
	@Override
	public void processQualityCheckRecord(String filelocation) throws IOException, ParseException {
		Reader in = new FileReader(filelocation);
		Iterable<CSVRecord> records =SurveyUtil.getQualityCheckRecords(in);
		ArrayList<QualityCheck>  list = new ArrayList();
		records.iterator().next();
		for (CSVRecord record : records) {
			list.add(convertDTO(record));
		}
		qualityCheckRepository.saveAll(list);
	}

	private QualityCheck convertDTO(CSVRecord record) throws ParseException {

		String startTime =record.get("starttime");
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
				Block block = blockRepository.findBlockByBlockCode(Long.valueOf(record.get("block")));
		if(block != null)qualityCheck.setBlock(block);
		}
		if(StringUtils.isNotEmpty(record.get("district"))){
			District district = districtRepository.findDistrictsByDistricCode(Long.valueOf(record.get("district")));
			if(district != null)qualityCheck.setDistrict(district);
		}
		
		if(StringUtils.isNotEmpty(record.get("UR"))){
			UrCode urCode = urCodeRepository.findUrcodeByUrcode(Long.valueOf(record.get("UR")));
			if(urCode != null)qualityCheck.setUrCode(urCode);
		}
		return qualityCheck;
	
	}

	@Override
	public Page<QualityCheckData> getAllQualityChecks(Long blockId, Long districtId, String searchString, Long pageNum, Long pageSize) {
		return aualityCheckDAO.findAllData(blockId, districtId, searchString, pageNum, pageSize);
	}

}
