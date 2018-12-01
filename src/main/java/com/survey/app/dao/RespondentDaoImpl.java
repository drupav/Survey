package com.survey.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.survey.app.dto.AllSubmissionMetaData;
import com.survey.app.dto.InterviewerData;
import com.survey.app.dto.RespondentData;
import com.survey.app.dto.TreeViewData;
import com.survey.app.model.District;
import com.survey.app.model.Respondent;
import com.survey.app.repository.BlockRepository;
import com.survey.app.repository.DistrictRepository;
import com.survey.app.util.JdbcSupport;
import com.survey.app.util.Page;
import com.survey.app.util.PaginationHelper;

@Repository
public class RespondentDaoImpl extends JpaDao<Respondent, Long> implements RespondentDao{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private BlockRepository blockRepository;
	
	private final PaginationHelper<RespondentData> paginationHelper = new PaginationHelper<RespondentData>();
	private final PaginationHelper<InterviewerData> paginationinterviewerdata = new PaginationHelper<InterviewerData>();
	public RespondentDaoImpl() {
		super(Respondent.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<RespondentData> findAllRespondents(Long blockId, Long districtId,Long regionId,Long interviewerId,String searchString,
			Long pageNum, Long pageSize) {
		
	       RespondentMapper mapper = new RespondentMapper();
	       final StringBuilder sqlBuilder = new StringBuilder(200);
	       final StringBuilder sqlCountBuilder = new StringBuilder(200);
	       sqlBuilder.append("select SQL_CALC_FOUND_ROWS ");
	       sqlBuilder.append(mapper.schema());
	       sqlCountBuilder.append(mapper.countSchema());
	       if(StringUtils.isNotBlank(searchString)){
	    	   sqlBuilder.append(" and( res.village_name like '%" + searchString + "%' OR res.respondent_name like '%" + searchString + "%' ) ");
	    	   sqlCountBuilder.append(" and( res.village_name like '%" + searchString + "%' OR res.respondent_name like '%" + searchString + "%' ) ");
	       }
			if(blockId != 0){
				sqlBuilder.append(" and block.id ="+blockId);
				sqlCountBuilder.append(" and block.id ="+blockId);
				
			} if(districtId != 0){
				sqlBuilder.append(" and dis.id ="+districtId);
				sqlCountBuilder.append(" and block.id ="+blockId);
				
			}if(regionId != 0){
				sqlBuilder.append(" and region.id ="+regionId);
				sqlCountBuilder.append(" and block.id ="+blockId);
			}
			
			sqlBuilder.append(" group by res.id");
			//sqlCountBuilder.append(" group by res.id");
			
			if (pageSize != 0) {
	            sqlBuilder.append(" limit ").append(pageSize);
	        }

	        if (pageNum != 0) {
	            sqlBuilder.append(" offset ").append(pageNum);
	        }
		//	int completeSampple =  this.jdbcTemplate.queryForObject(sqlCountBuilder.toString(), new Object[] {  }, Integer.class);
			List<District> districts=districtRepository.findAll();
			Long totalSamples= districtId != 0?600l:districts.stream().mapToLong(District::getTotalSamples).sum();
			if(blockId !=0){
				totalSamples=blockRepository.findById(blockId).get().getTotalSamples();
			}
			final String sqlCountRows = "SELECT FOUND_ROWS()";
			
			return this.paginationHelper.fetchPage(this.jdbcTemplate, sqlCountRows, sqlBuilder.toString(),
	                new Object[] { }, mapper,0,totalSamples);
		}
	
	private static final class RespondentMapper implements RowMapper<RespondentData> {

		public String schema() {
			return "  res.id as id,region.region_name as regionName,dis.district_name as districtName,block.block_name as blockName," +
				   " res.village_name as villageName,res.interviewer_name as interviewerName,res.respondent_name as respondentName," +
					" res.audio as audio,res.sample_num as samplenum,res.submission_date as lastSubmission,ur.ur_name as urName,"
					+ "res.awc_code as awcCode,res.awc_name as awcName,hsc_name as hscName,res.ward_id as ward,address as address,contact_num as contactNum,"
					+ " result_status as resultStatus,res.start_time as startTime,res.duration as duration"
					+ " from respondent res " +
					" left join block block ON block.id = res.block_id"
					+ " left join ur_code ur ON ur.id = res.ur_id" +
					" left join district dis ON dis.id = block.district_id"
					+ " left join region ON region.id = dis.region_id where 1=1 ";
		}
		
		public String countSchema() {
			return " select count(*) "
					+ " from respondent res " +
					" left join block block ON block.id = res.block_id"
					+ " left join ur_code ur ON ur.id = res.ur_id" +
					" left join district dis ON dis.id = block.district_id"
					+ " left join region ON region.id = dis.region_id where 1=1 ";
		}

		@Override
		public RespondentData mapRow(final ResultSet rs,
				@SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			String regionName = rs.getString("regionName");
			String districtName = rs.getString("districtName");
			String blockName = rs.getString("blockName");
			String villageName = rs.getString("villageName");
			String interviewerName = rs.getString("interviewerName");
			String respondentName = rs.getString("respondentName");
			String audio = rs.getString("audio");
			Long samplenum = rs.getLong("samplenum");
			String awcName = rs.getString("awcName");
			String awcCode = rs.getString("awcCode");
			String hscName = rs.getString("hscName");
			String ward = rs.getString("ward");
			String urName = rs.getString("urName");
			String address = rs.getString("address");
			String contactNum = rs.getString("contactNum");
			String resultStatus = rs.getString("resultStatus");
			LocalDate lastSubmission = JdbcSupport.getLocalDate(rs, "lastSubmission");
			DateTime startTime=JdbcSupport.getDateTime(rs,"startTime");
			Long duration = rs.getLong("duration");
			
			return new RespondentData(id,regionName,districtName,blockName,villageName,interviewerName,respondentName,audio,samplenum,lastSubmission,
					awcName,hscName,ward,address,contactNum,resultStatus,startTime,duration,awcCode,urName);
			

		}
	}
	
	private static final class AllSubmissonMetaMapper implements RowMapper<AllSubmissionMetaData> {

		public String schema() {
			return "  * from ( select  res.district_id as id,null as districtId, district.district_name as districtName, null as blockName,"
					+ " district.total_samples as totalSamples,count(res.block_id) as completed,(district.total_Samples-count(res.block_id)) as pending,"
					+ " round((count(res.block_id)/district.total_Samples)*100,1) as completePercent,"
					+ " round(((district.total_Samples-count(res.block_id))/district.total_samples)*100,1) pedingpercent,max(submission_date) subDate "
					+ " from respondent res"
					+ " left join block ON block.id = res.block_id"
					+ " left join district ON district.id = res.district_id"
					+ " group by res.district_id"
					+ " union "
					+ " select  res.block_id as id,res.district_id as districtId, null as districtName, block.block_name as blockName,"
							+ "block.total_samples as totalSamples,count(res.block_id) as completed,(block.total_Samples-count(res.block_id)) as pending,"
							+ "round((count(res.block_id)/block.total_Samples)*100,1) as completePercent,"
							+ "round(((block.total_Samples-count(res.block_id))/block.total_samples)*100,1) pedingpercent,max(submission_date) subDate "
							+ " from respondent res"
							+ " left join block ON block.id = res.block_id"
							+ " left join district ON district.id = res.district_id"
							+ " group by res.block_id ) as allsub  where 1=1 ";
		}
		

		@Override
		public AllSubmissionMetaData mapRow(final ResultSet rs,
				@SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			Long districtId = rs.getLong("districtId");
			String districtName = rs.getString("districtName");
			String blockName = rs.getString("blockName");
			Long totalSamples = rs.getLong("totalSamples");
			Long completed = rs.getLong("completed");
			Long pending = rs.getLong("pending");
			LocalDate lastSubmission = JdbcSupport.getLocalDate(rs, "subDate");
			Double completePercent = rs.getDouble("completePercent");
			Double pedingpercent = rs.getDouble("pedingpercent");
			return new AllSubmissionMetaData(id,districtId,districtName,blockName,completed,pending,completePercent,pedingpercent,totalSamples,lastSubmission);
		}
	}
	private static final class InterviewerDataMapper implements RowMapper<InterviewerData> {

		public String schema() {
			return " res.id as id, res.block_id as blockId,res.district_id as districtId, district.district_name as districtName, "
					+ " block.block_name as blockName,"
					+ " res.interviewer_code interviewerCode,res.interviewer_name as interviewerName,count(res.id)	noOfsamples"
					+ " from respondent res"
					+ " left join block ON block.id = res.block_id"
					+ " left join district ON district.id = block.district_id"
					+ "   where 1=1";
		}
		

		@Override
		public InterviewerData mapRow(final ResultSet rs,
				@SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			Long districtId = rs.getLong("districtId");
			Long blockId = rs.getLong("blockId");
			String districtName = rs.getString("districtName");
			String blockName = rs.getString("blockName");
			String interviewerCode = rs.getString("interviewerCode");
			String interviewerName = rs.getString("interviewerName");
			Long noOfsamples = rs.getLong("noOfsamples");
			return new InterviewerData(id,districtId,districtName,blockName,interviewerCode,interviewerName,noOfsamples);
		}
	}
	private static final class InterviewerQCDataMapper implements RowMapper<InterviewerData> {

		public String schema() {
			return "  qc.id as id,district.district_name as districtName,block.block_name as blockName,qc.interviewer_name as interviewername,"
					+ "COUNT(CASE WHEN qc.accompaniment ='Y' THEN 1 END) as accompaniments,"
					+ "COUNT(CASE WHEN qc.spot_check ='Y' THEN 1 END) as spotChecks,"
					+ "COUNT(CASE WHEN qc.back_check ='Y' THEN 1 END) as backChecks,"
					+ "COUNT(CASE WHEN qc.audio_check ='Y' THEN 1 END) as audioChecks"
					+ " from quality_check qc "
					+ " left join block ON block.id = qc.block_id"
					+ " left join district ON district.id = block.district_id"
					+ " where qc.interviewer_name is not null ";
		}
		

		@Override
		public InterviewerData mapRow(final ResultSet rs,
				@SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			String districtName = rs.getString("districtName");
			String blockName = rs.getString("blockName");
			String interviewerName = rs.getString("interviewername");
			Long accompaniments = rs.getLong("accompaniments");
			Long spotChecks = rs.getLong("spotChecks");
			Long backChecks = rs.getLong("backChecks");
			Long audioChecks = rs.getLong("audioChecks");
			return new InterviewerData(id,districtName,blockName,interviewerName,accompaniments,spotChecks,backChecks,audioChecks);
		}
	}
	
	private static final class InterviewerListDataMapper implements RowMapper<InterviewerData> {

		public String schema() {
			return "  res.interviewer_code as interviewCode,res.interviewer_name as interviewername,count(res.id) as totalSamples"
					+ " from respondent res";
		}
		

		@Override
		public InterviewerData mapRow(final ResultSet rs,
				@SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			String interviewCode = rs.getString("interviewCode");
			String interviewerName = rs.getString("interviewername");
			Long totalSamples = rs.getLong("totalSamples");
			return new InterviewerData(interviewCode,interviewerName,totalSamples);
		}
	}
	@Override
	public TreeViewData getMetaData(Long page, Long size, Long blockId, Long districtId) {
		
		AllSubmissonMetaMapper mapper = new AllSubmissonMetaMapper();
	       final StringBuilder sqlBuilder = new StringBuilder(200);
	       sqlBuilder.append("select SQL_CALC_FOUND_ROWS ");
	       sqlBuilder.append(mapper.schema());
	      
			/*if(blockId != 0){
				sqlBuilder.append(" id  ="+blockId+" and blockName is not null");
				
			} if(districtId != 0){
				sqlBuilder.append(" and districtId ="+districtId+" or (id  = "+districtId+" and districtName is not null)");
			}*/
			
		//	sqlBuilder.append(" group by id");
			final String sqlCountRows = "SELECT FOUND_ROWS()";
			List<District> districts=districtRepository.findAll();
			Long totalSamples= districtId != 0?600l:districts.stream().mapToLong(District::getTotalSamples).sum();
			if(blockId !=0){
				totalSamples=blockRepository.findById(blockId).get().getTotalSamples();
			}
			 final List<AllSubmissionMetaData> items = this.jdbcTemplate.query(sqlBuilder.toString(),  new Object[] { }, mapper);

		        // determine how many rows are available
		       /* final int totalFilteredRecords = this.jdbcTemplate.queryForObject(
		        		sqlCountRows, new Object[] {  }, Integer.class);*/
		        return new TreeViewData(items,totalSamples,totalSamples);
		}

	@Override
	public Page<InterviewerData> getInterviewerData(Long blockId, Long districtId, String searchString, Long page,
			Long size) {
		List<District> districts=districtRepository.findAll();
		Long totalSamples= districtId != 0?600l:districts.stream().mapToLong(District::getTotalSamples).sum();
		final String sqlCountRows = "SELECT FOUND_ROWS()";
		InterviewerDataMapper mapper = new InterviewerDataMapper();
	       final StringBuilder sqlBuilder = new StringBuilder(200);
	       sqlBuilder.append("select SQL_CALC_FOUND_ROWS ");
	       sqlBuilder.append(mapper.schema());
	       if(StringUtils.isNotBlank(searchString)){
	    	   sqlBuilder.append(" and( res.interviewer_name like '%" + searchString + "%' OR res.interviewer_code = " + searchString+ " ) ");
	       }
			if(blockId != 0){
				sqlBuilder.append(" and block.id ="+blockId);
				
			} if(districtId != 0){
				sqlBuilder.append(" and district.id ="+districtId);
				
			}
			
			sqlBuilder.append(" group by res.block_id,res.interviewer_code");
			Page<InterviewerData> intrvwpage = this.paginationinterviewerdata.fetchPage(this.jdbcTemplate, sqlCountRows, sqlBuilder.toString(),
	                new Object[] { }, mapper,0,totalSamples);
			Long totalInterviews=intrvwpage.getPageItems().stream().mapToLong(i -> i.getCompletedSamples()).sum();
			if (size != 0) {
	            sqlBuilder.append(" limit ").append(size);
	        }

	        if (page != 0) {
	            sqlBuilder.append(" offset ").append(page);
	        }
		//	int completeSampple =  this.jdbcTemplate.queryForObject(sqlCountBuilder.toString(), new Object[] {  }, Integer.class);
			
			if(blockId !=0){
				totalSamples=blockRepository.findById(blockId).get().getTotalSamples();
			}
			
			
		 intrvwpage = this.paginationinterviewerdata.fetchPage(this.jdbcTemplate, sqlCountRows, sqlBuilder.toString(),
	                new Object[] { }, mapper,0,totalSamples);
			
			intrvwpage.setTotalFilterRecords(totalInterviews);
			if(StringUtils.isNoneEmpty(searchString)){
				intrvwpage.setTotalInterviews(totalInterviews);
			}
			return intrvwpage;
		}

	@Override
	public Page<InterviewerData> getinterviewierQualitycheckData(Long blockId, Long districtId, String searchString,
			Long page, Long size) {

		
		InterviewerQCDataMapper mapper = new InterviewerQCDataMapper();
	       final StringBuilder sqlBuilder = new StringBuilder(200);
	       sqlBuilder.append("select SQL_CALC_FOUND_ROWS ");
	       sqlBuilder.append(mapper.schema());
	       if(StringUtils.isNotBlank(searchString)){
	    	   sqlBuilder.append(" and( qc.interviewer_name like '%" + searchString + "%' ) ");
	       }
			if(blockId != 0){
				sqlBuilder.append(" and qc.block_id ="+blockId);
				
			} if(districtId != 0){
				sqlBuilder.append(" and qc.district_id ="+districtId);
				
			}
			
			sqlBuilder.append(" group by qc.block_id,qc.interviewer_name");
			
			if (size != 0) {
	            sqlBuilder.append(" limit ").append(size);
	        }

	        if (page != 0) {
	            sqlBuilder.append(" offset ").append(page);
	        }
		//	int completeSampple =  this.jdbcTemplate.queryForObject(sqlCountBuilder.toString(), new Object[] {  }, Integer.class);
			final String sqlCountRows = "SELECT FOUND_ROWS()";
			
			return this.paginationinterviewerdata.fetchPage(this.jdbcTemplate, sqlCountRows, sqlBuilder.toString(),
	                new Object[] { }, mapper,0,0l);
		
	}

	@Override
	public Page<InterviewerData> getinterviewList(Long page, Long size) {

		final String sqlCountRows = "SELECT FOUND_ROWS()";
		InterviewerListDataMapper mapper = new InterviewerListDataMapper();
	       final StringBuilder sqlBuilder = new StringBuilder(200);
	       sqlBuilder.append("select SQL_CALC_FOUND_ROWS ");
	       sqlBuilder.append(mapper.schema());
			sqlBuilder.append(" group by res.interviewer_code");
			if (size != 0) {
	            sqlBuilder.append(" limit ").append(size);
	        }
	        if (page != 0) {
	            sqlBuilder.append(" offset ").append(page);
	        }
	        return this.paginationinterviewerdata.fetchPage(this.jdbcTemplate, sqlCountRows, sqlBuilder.toString(),
	                new Object[] { }, mapper,0,0l);
	}
}
