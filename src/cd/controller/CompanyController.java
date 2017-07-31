package cd.controller;

import java.sql.Array;
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
	@Autowired
	CompanyService cs =new CompanyService();
	
	@RequestMapping("/")
	public String showhome() {
		return "home";
	}
    
    @RequestMapping("addcomp")
    public String addcom(HttpServletRequest request) {
    	String cname=request.getParameter("cname");
    	String location=request.getParameter("location");
    	if(cs.alreadyexists(cname))
    		return "alreadyexists";
    	boolean s=cs.addcompany(cname,location);
    	return "success";
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
    	public String deleting(Model model ,@RequestParam(value="companyname",required=false)String id ) {
    		
    		List<Company> list=cs.del(id);
    		model.addAttribute("list",list);
    		return "listcompany";
    	}
    	
    	@RequestMapping(value="edit",method=RequestMethod.GET)
    	public String editcom(HttpSession session,Model model,@RequestParam(value="companyname", required=false)String id) {
    		//String name=id;
    		//model.addAttribute(name);
    		session.setAttribute("name", id);
    		return "editcompany";
    	}
    	
    	@RequestMapping(value="addemp",method=RequestMethod.GET)
    	public String addemp(Model model,HttpSession session,@RequestParam(value="companyname" ,required=false)String id) {
    		session.setAttribute("name",id);
    		return "addemployee";
    	}
    	
    	@RequestMapping("editsuc")
    	public String editsuc(Model model,HttpServletRequest request,HttpSession session) {
    		//String oldname="s";
    		String oldname=(String) session.getAttribute("name");
    		String cname=request.getParameter("cname");
    		String location=request.getParameter("loc");
    		List<Company> list=cs.editcom(oldname,cname,location);
    		session.invalidate();
    		model.addAttribute("list",list);
    		return "listcompany";
    	}
    	
    	@RequestMapping("emp")
    	public String addemployee(Model model,HttpSession session,HttpServletRequest request) {
    		String cname=(String) session.getAttribute("name");
    		String eid=request.getParameter("eid");
    		String ename=request.getParameter("ename");
    		List<Employee> list=cs.addtoemp(cname,eid,ename);
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
    	public String editemp(HttpSession session,Model model,@RequestParam(value="eid",required=false)String eid,@RequestParam(value="cname",required=false)String cname) {
    		session.setAttribute("cname", cname);
    		session.setAttribute("eid", eid);
    		return "newemployee";
    	}
    	
    	@RequestMapping("newemp")
    	public String editcomplete(Model model,HttpSession session,HttpServletRequest request) {
    		String oldcname=(String) session.getAttribute("cname");
    		String oldeid=(String) session.getAttribute("eid");
    		String eid=request.getParameter("eid");
    		String ename=request.getParameter("ename");
    		String cname=request.getParameter("cname");
    		session.invalidate();
    		List<Employee> list=cs.editemp(oldcname,oldeid,eid,ename,cname);
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

