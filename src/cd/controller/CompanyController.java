package cd.controller;

import java.sql.Array;
import java.util.regex.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cd.service.Company;
import cd.service.CompanyService;
import cd.service.Employee;

@Controller
public class CompanyController {
	
	
	public int id;
	
	@Autowired
	CompanyService cs =new CompanyService();
	
	@RequestMapping("/")
	public String showhome() {
		return "home";
	}
    
    @RequestMapping("addcomp")
    public String addcom(HttpServletRequest request) {
    	String id=request.getParameter("id");
    	if(!Pattern.matches("[1-9]*", id)) {
    		return "error";
    	}
    	String cname=request.getParameter("cname");
    	String location=request.getParameter("location");
    	if(id.isEmpty()||cname.isEmpty()||location.isEmpty()) {
    		return "error";
    	}
    	boolean s=cs.addcompany(id,cname,location);
    	if(s) {
    	return "success";
    	}
    	else 
    		return "alreadyexists";
    }
	/*@RequestMapping("addordelete")
	public String addordelete(HttpServletRequest request) {
		String action= request.getParameter("action1");
		if(action=="Add") {
			return "addcompany";
		}
		else if(request.getParameter("action2")=="Delete")
			return "deletecompany";
		else 
			return "home";
	}*/
	@RequestMapping(value="addordelete",params="action1",method=RequestMethod.POST)
    public String action1()
    {
        return "addcompany";
    }
    @RequestMapping(value="addordelete",params="action2",method=RequestMethod.POST)
    public String action2(Model model)
    {
    	List<Company> list=cs.getlist();
    	model.addAttribute("list",list);
        return "listcompany";
    }
    
    @RequestMapping("delcomp")
    public String delcom(HttpServletRequest request) {
    	String cname=request.getParameter("cname");
    	boolean i=cs.delcomp(cname);
    	if(i==true)
    		return "delsuccess";
    	else
    		return "delunsuccess";
    	
    }
    	
    	
    	@RequestMapping(value="delete",method=RequestMethod.GET)
    	public String deleting(Model model ,@RequestParam(value="cid",required=false)String id ) {
    		
    		List<Company> list=cs.del(id);
    		model.addAttribute("list",list);
    		return "listcompany";
    	}
    	
    	@RequestMapping(value="edit",method=RequestMethod.GET)
    	public String editcom(HttpSession session,Model model,@RequestParam(value="cid", required=false)String id) {
    		//String name=id;
    		session.setAttribute("id", id);//model.addAttribute(name);
       		return "editcompany";
    	}
    	
    	@RequestMapping(value="addemp",method=RequestMethod.GET)
    	public String addemp(Model model,HttpSession session,@RequestParam(value="cid" ,required=false)String id) {
    		session.setAttribute("id",id);
    		return "addemployee";
    	}
    	
    	@RequestMapping("editsuc")
    	public String editsuc(Model model,HttpServletRequest request,HttpSession session) {
    		//String oldname="s";
    		String oid= (String) session.getAttribute("id");
    		String newid=request.getParameter("id");
    		if(!Pattern.matches("[1-9]*", newid)) {
        		return "error";
        	}
    		String cname=request.getParameter("cname");
    		String location=request.getParameter("loc");
    		List<Company> list=cs.editcom(oid,newid,cname,location);
    		if(list==null) {
    			return "alreadyexists";
    		}
    		session.invalidate();
    		model.addAttribute("list",list);
    		return "listcompany";
    	}
    	
    	@RequestMapping("emp")
    	public String addemployee(Model model,HttpSession session,HttpServletRequest request) {
    		String cid=(String) session.getAttribute("id");
    		String eid=request.getParameter("eid");
    		if(!Pattern.matches("[1-9]*", eid)) {
        		return "error";
        	}
    		String ename=request.getParameter("ename");
    		List<Employee> list=cs.addtoemp(cid,eid,ename);
    		if(list==null) {
    			return "alreadyexists";
    		}
    		session.invalidate();
    		model.addAttribute("list",list);
    		return "listemployee";
    	}
    	
    	@RequestMapping(value="deleteemployee",method=RequestMethod.GET)
    	public String deleteemp(Model model,@RequestParam(value="eid",required=false)String eid) {
    		List<Employee> list=cs.delemp(eid);
    		model.addAttribute("list",list);
    		return "listemployee";
    	}
    	
    	@RequestMapping(value="editemployee",method=RequestMethod.GET)
    	public String editemp(HttpSession session,Model model,@RequestParam(value="eid",required=false)String eid,@RequestParam(value="cname",required=false)String cid) {
    		session.setAttribute("cid", cid);
    		session.setAttribute("eid", eid);
    		return "newemployee";
    	}
    	
    	@RequestMapping("newemp")
    	public String editcomplete(Model model,HttpSession session,HttpServletRequest request) {
    		String cid=(String) session.getAttribute("cid");
    		String oldeid=(String) session.getAttribute("eid");
    		String eid=request.getParameter("eid");
    		if(!Pattern.matches("[1-9]*", eid)) {
        		return "error";
        	}
    		String ename=request.getParameter("ename");
    		
    		session.invalidate();
    		List<Employee> list=cs.editemp(cid,oldeid,eid,ename);
    		if(list==null) {
    			return "alreadyexists";
    		}
    		model.addAttribute("list",list);
    		return "listemployee";
    	}
    	
    	@RequestMapping("search")
    	public String search(Model model,HttpServletRequest request) {
    		String key=request.getParameter("search");
    		List<Employee> list=cs.searchkey(key);
    		model.addAttribute("list",list);
    		return "listemployee";
    	}
    	
    	@RequestMapping("listemp")
    	public String listemp(Model model) {
    		List<Employee> list=cs.getallemp();
    		model.addAttribute("list",list);
    		return "listemployee";
    	}
    }

