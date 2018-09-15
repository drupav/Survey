package com.survey.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.survey.app.dto.RespondentData;
import com.survey.app.model.Respondent;
import com.survey.app.util.JdbcSupport;
import com.survey.app.util.Page;
import com.survey.app.util.PaginationHelper;

@Repository
public class RespondentDaoImpl extends JpaDao<Respondent, Long> implements RespondentDao{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	private final PaginationHelper<RespondentData> paginationHelper = new PaginationHelper<RespondentData>();
	public RespondentDaoImpl() {
		super(Respondent.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<RespondentData> findAllRespondents(Long blockId, Long districtId,Long regionId,Long interviewerId,String searchString,
			Long pageNum, Long pageSize) {
		
	       RespondentMapper mapper = new RespondentMapper();
	       final StringBuilder sqlBuilder = new StringBuilder(200);
	       sqlBuilder.append("select SQL_CALC_FOUND_ROWS ");
	       sqlBuilder.append(mapper.schema());
			
	       if(StringUtils.isNotBlank(searchString)){
	    	   sqlBuilder.append(" and( res.village_name like '%" + searchString + "%' OR res.respondent_name like '%" + searchString + "%' ) ");
	       }
			if(blockId != 0){
				sqlBuilder.append(" and block.id ="+blockId);
				
			} if(districtId != 0){
				sqlBuilder.append(" and dis.id ="+districtId);
				
			}if(regionId != 0){
				sqlBuilder.append(" and region.id ="+regionId);
			}
			
			sqlBuilder.append(" group by res.id");
			if (pageSize != 0) {
	            sqlBuilder.append(" limit ").append(pageSize);
	        }

	        if (pageNum != 0) {
	            sqlBuilder.append(" offset ").append(pageNum);
	        }
			
			final String sqlCountRows = "SELECT FOUND_ROWS()";
			return this.paginationHelper.fetchPage(this.jdbcTemplate, sqlCountRows, sqlBuilder.toString(),
	                new Object[] { }, mapper);
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



}
