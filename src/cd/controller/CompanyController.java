package cd.controller;

import java.sql.Array;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cd.service.Company;
import cd.service.CompanyService;

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
    }

