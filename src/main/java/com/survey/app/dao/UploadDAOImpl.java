package com.survey.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.survey.app.dto.UploadData;
import com.survey.app.model.FileUpload;
import com.survey.app.util.JdbcSupport;
import com.survey.app.util.Page;
import com.survey.app.util.PaginationHelper;

@Repository
public class UploadDAOImpl extends JpaDao<FileUpload, Long> implements UploadDAO{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	private final PaginationHelper<UploadData> paginationHelper = new PaginationHelper<UploadData>();
	public UploadDAOImpl() {
		super(FileUpload.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<UploadData> findAllUploads(Long pageNum, Long pageSize) {
		
	       RespondentMapper mapper = new RespondentMapper();
	       final StringBuilder sqlBuilder = new StringBuilder(200);
	       sqlBuilder.append("select SQL_CALC_FOUND_ROWS ");
	       sqlBuilder.append(mapper.schema());
			
			sqlBuilder.append("group by file.id");
			if (pageSize != 0) {
	            sqlBuilder.append(" limit ").append(pageSize);
	        }

	        if (pageNum != 0) {
	            sqlBuilder.append(" offset ").append(pageNum);
	        }
			
			final String sqlCountRows = "SELECT FOUND_ROWS()";
			return this.paginationHelper.fetchPage(this.jdbcTemplate, sqlCountRows, sqlBuilder.toString(),
	                new Object[] { }, mapper,0,0l);
		}
	
	private static final class RespondentMapper implements RowMapper<UploadData> {

		public String schema() {
			return "  file.id as id,file.file_name as fileName,file.upload_type as uploadType,file.file_location as fileLocation,file.upload_date as uploadDate,"
					+ " file.comments as comments, u.username as userName"
					+ " from file_upload file"
					+ " left join users u on u.id = file.created_by ";
		}

		@Override
		public UploadData mapRow(final ResultSet rs,
				@SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			String fileName = rs.getString("fileName");
			String fileLocation = rs.getString("fileLocation");
			String uploadType = rs.getString("uploadType");
			String userName = rs.getString("userName");
			LocalDate uploadDate = JdbcSupport.getLocalDate(rs, "uploadDate");
			String comments = rs.getString("comments");
			
			return new UploadData(id,fileName,fileLocation,uploadDate,userName,comments,uploadType);
		}
	}
}
