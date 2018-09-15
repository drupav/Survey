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
import com.survey.app.job.dto.QualityCheckData;
import com.survey.app.model.QualityCheck;
import com.survey.app.model.Respondent;
import com.survey.app.util.JdbcSupport;
import com.survey.app.util.Page;
import com.survey.app.util.PaginationHelper;

@Repository
public class QualityCheckDAOImpl extends JpaDao<QualityCheck, Long> implements QualityCheckDAO{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	private final PaginationHelper<QualityCheckData> paginationHelper = new PaginationHelper<QualityCheckData>();
	public QualityCheckDAOImpl() {
		super(QualityCheck.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<QualityCheckData> findAllData(Long blockId, Long districtId,String searchString,Long pageNum, Long pageSize) {
		
	       QualityCheckMapper mapper = new QualityCheckMapper();
	       final StringBuilder sqlBuilder = new StringBuilder(200);
	       sqlBuilder.append("select SQL_CALC_FOUND_ROWS ");
	       sqlBuilder.append(mapper.schema());
			
	       if(StringUtils.isNotBlank(searchString)){
	    	   sqlBuilder.append(" and(qc.village_name like '%" + searchString + "%' OR qc.interviewer_name like '%" + searchString + "%' OR qc.respondent_name like '%" + searchString + "%' ) ");
	       }
			if(blockId != 0){
				sqlBuilder.append(" and block.id ="+blockId);
				
			} if(districtId != 0){
				sqlBuilder.append(" and dis.id ="+districtId);
			}
			sqlBuilder.append(" group by qc.id");
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
 private final class QualityCheckMapper implements RowMapper<QualityCheckData> {

		public String schema() {
			return "  qc.id as id,dis.district_name as districtName,block.block_name as blockName,"
					+ "qc.village_name as villageName,qc.interviewer_name as interviewerName, ur.ur_name as urname,"
					+ "qc.ward_name as wardName,qc.awc_name as awcName,qc.start_time as startTime,qc.end_time as endTime,"
					+ "case when qc.accompaniment = 'Y' then 'Yes' else 'No' end  accompaniment,"
					+ "case when qc.spot_check = 'Y' then 'Yes' else 'No' end  spotcheck,"
					+ " case when qc.back_check = 'Y' then 'Yes' else 'No' end  backcheck,"
					+ " case when qc.audio_check = 'Y' then 'Yes' else 'No' end  audiocheck"
					+ "  from quality_check qc "
					+ " left join block block ON block.id = qc.block_id"
					+ " left join district dis ON dis.id = qc.district_id"
					+ " left join ur_code ur on ur.id = qc.ur_id where 1=1";
		}

		@Override
		public QualityCheckData mapRow(final ResultSet rs,
				@SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			String districtName = rs.getString("districtName");
			String blockName = rs.getString("blockName");
			String villageName = rs.getString("villageName");
			String interviewerName = rs.getString("interviewerName");
			String urname = rs.getString("urname");
			String wardName = rs.getString("wardName");
			String awcName = rs.getString("awcName");
			DateTime startTime=JdbcSupport.getDateTime(rs,"startTime");
			DateTime endTime=JdbcSupport.getDateTime(rs,"endTime");
			String accompaniment = rs.getString("accompaniment");
			String spotcheck = rs.getString("spotcheck");
			String backcheck = rs.getString("backcheck");
			String audiocheck = rs.getString("audiocheck");
			return new QualityCheckData(id,districtName,blockName,villageName,interviewerName,urname,wardName,awcName,startTime,endTime,accompaniment,
					spotcheck,backcheck,audiocheck);
			

		}

	}
}
