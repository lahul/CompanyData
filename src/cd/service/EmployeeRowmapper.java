package cd.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowmapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet arg0, int arg1) throws SQLException {
		Employee emp=new Employee();
		emp.setEid(arg0.getString("eid"));
		emp.setEname(arg0.getString("empname"));
		emp.setCname(arg0.getString("company_name"));
		return emp;
	}

}
