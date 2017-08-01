package cd.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CompanyRowmapper implements RowMapper<Company>{

	@Override
	public Company mapRow(ResultSet rs, int arg1) throws SQLException {
		Company com=new Company();
		com.setId(rs.getString("id"));
		com.setCompany_name(rs.getString("company_name"));
		com.setLocation(rs.getString("location"));
		return com;
	}
	

}
