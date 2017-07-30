package cd.service;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public boolean addcompany(String cname,String location) {
		String query= "insert into companies(company_name,location) values(:cname,:location)";
		HashMap<String,String> hm=new HashMap<>();
		hm.put("cname", cname);
		hm.put("location", location);
		boolean i=namedParameterJdbcTemplate.update(query, hm)==1;
		return i;
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
	
	public List<Company> del(String cname){
		String query="delete from companies where company_name=:cname";
		HashMap<String,String> hm=new HashMap<>();
		hm.put("cname", cname);
		namedParameterJdbcTemplate.update(query, hm);
		List<Company> list=getlist();
		return list;
	}
	
	public List<Company> editcom(String oldname,String cname,String location){
		String query="update companies set company_name=:cname,location=:location where company_name=:oname";
		HashMap<String,String> hm=new HashMap<>();
		hm.put("cname", cname);
		hm.put("location", location);
		hm.put("oname", oldname);
		namedParameterJdbcTemplate.update(query, hm);
		List<Company> list=getlist();
		return list;
	}
}
