package cd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String action2()
    {
        return "delcompany";
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
}
