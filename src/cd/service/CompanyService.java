package cd.service;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyService {

	public CompanyService() {
		
	}
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate =new NamedParameterJdbcTemplate(dataSource);
	}
	
	public boolean addcompany(String id,String cname,String location) {
		String query1="select * from companies where id=:id;";
		HashMap<String,String> hm1=new HashMap<>();
		hm1.put("id", id);
		List list=namedParameterJdbcTemplate.queryForList(query1, hm1);
		if(list.size()>0) {
			return false;
		}
		String query2= "insert into companies(id,company_name,location) values(:id,:cname,:location)";
		HashMap<String,String> hm2=new HashMap<>();
		hm2.put("id", id);
		hm2.put("cname", cname);
		hm2.put("location", location);
		boolean i=namedParameterJdbcTemplate.update(query2, hm2)==1;
		return true;
	}
	
	public List<Company> getlist() {
		String query="select *from companies";
		List<Company> list=namedParameterJdbcTemplate.query(query, new CompanyRowmapper());
		return list;
	}
	
	public boolean alreadyexists(String cname) {
		String query="select * from companies where company_name=:cname";
		HashMap<String,String> hm=new HashMap<>();
		hm.put("cname", cname);
		List<Company> list=namedParameterJdbcTemplate.query(query, hm,new CompanyRowmapper());
		if(list.size()==0)
			return false;
		else 
			return true;
	}
	

	
	public boolean delcomp(String cname) {
		String query="delete from companies where company_name=:cname";
		HashMap<String,String> hm=new HashMap<>();
		hm.put("cname", cname);
		boolean i=namedParameterJdbcTemplate.update(query, hm)==1;
		return i;
	}
	
	public List<Company> del(String cid){
		String query="delete from companies where id=:cid";
		HashMap<String,String> hm=new HashMap<>();
		hm.put("cid", cid);
		namedParameterJdbcTemplate.update(query, hm);
		List<Company> list=getlist();
		return list;
	}
	
	public List<Company> editcom(String oid,String newid,String cname,String location){
		String query1="select * from companies where id=:id;";
		HashMap<String,String> hm1=new HashMap<>();
		hm1.put("id", newid);
		List list2=namedParameterJdbcTemplate.queryForList(query1, hm1);
		if(list2.size()>0||newid==oid) {
			List list1 = null;
			return list1;
		}
		String query2="update companies set id=:newid,company_name=:cname,location=:location where id=:oid";
		HashMap<String,String> hm2=new HashMap<>();
		hm2.put("newid", newid);
		hm2.put("cname",cname);
		hm2.put("location",location);
		hm2.put("oid",oid );
		namedParameterJdbcTemplate.update(query2, hm2);
		List<Company> list=getlist();
		return list;
	}

	public List<Employee> addtoemp(String cid, String eid, String ename) {
		String query1="select * from employee where eid=:eid;";
		HashMap<String,String> hm1=new HashMap<>();
		hm1.put("eid", eid);
		List list=namedParameterJdbcTemplate.queryForList(query1, hm1);
		if(list.size()>0) {
			List list1 = null;
			return list1;
		}
		String query2="insert into employee values(:eid,:ename,:cid)"; 
		HashMap<String,String> hm2=new HashMap<>();
		hm2.put("eid", eid);
		hm2.put("ename", ename);
		hm2.put("cid", cid);
		namedParameterJdbcTemplate.update(query2, hm2);
		String query3="select *from employee";
		List<Employee> list2=namedParameterJdbcTemplate.query(query3, new EmployeeRowmapper());
		return list2;
	}

	public List<Employee> delemp(String eid) {
		String query1="delete from employee where eid=:eid";
		HashMap<String,String> hm=new HashMap<>();
		hm.put("eid", eid);
		namedParameterJdbcTemplate.update(query1, hm);
		String query2="select *from employee";
		List<Employee> list=namedParameterJdbcTemplate.query(query2, new EmployeeRowmapper());
		return list;
	}

	public List<Employee> editemp(String cid, String oldeid, String eid, String ename) {
		String query="select * from employee where eid=:eid";
		HashMap<String,String> hm1=new HashMap<>();
		hm1.put("eid", eid);
		List list2=namedParameterJdbcTemplate.queryForList(query, hm1);
		if(list2.size()>0||eid==oldeid) {
			List list1 = null;
			return list1;
		}
			String query1="update employee set eid=:eid,empname=:ename,cid=:cid where eid=:oldeid and cid=:cid";
			HashMap<String,String> hm=new HashMap<>();
			hm.put("eid", eid);
			hm.put("ename", ename);
			hm.put("cid", cid);
			hm.put("oldeid", oldeid);
			hm.put("cid", cid);
			namedParameterJdbcTemplate.update(query1, hm);
			String query2="select *from employee";
			List<Employee> list=namedParameterJdbcTemplate.query(query2, new EmployeeRowmapper());
			return list;
	}

	public List<Employee> getallemp() {
		String query="select employee.eid,employee.empname,companies.company_name from employee INNER JOIN companies on employee.cid=companies.id";
		List<Employee> list=namedParameterJdbcTemplate.query(query, new EmployeeRowmapper());
		return list;
	}

	public List<Employee> searchkey(String key) {
		String query="select employee eid,employee.empname,companies.company_name from employee"
				+ "Inner join companies where employee.cid=companies.id and"
				+ "eid=:key or empname=:key or cid=:key";
		HashMap<String,String> hm=new HashMap<>();
		hm.put("key", key);
		List<Employee> list=namedParameterJdbcTemplate.query(query, hm, new EmployeeRowmapper());
		return list;
	}
	
}
