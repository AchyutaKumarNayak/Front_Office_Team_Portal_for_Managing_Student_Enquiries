package in.achyuta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.achyuta.bindings.DashboardResponse;
import in.achyuta.service.StudentEnquiryServiceImpl;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentEnquiryController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private StudentEnquiryServiceImpl studentEnquiryService;
	
	
	@GetMapping("/logout")
    public String logout() {
		session.invalidate();
    	return "index";
    }
	
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		//Get the session id for specifeid user(Successful Logger) by using the key name 
		//in.achyuta.UserServiceImpl  line no 41
		Integer userId =(Integer)session.getAttribute("userId");
	    //Call StudentEnquiryService method for business logic
		DashboardResponse dashboardRes = studentEnquiryService.getDashboardRes(userId);
		//Add this binding class in UI
		model.addAttribute("dashboardRes", dashboardRes);
		//return dashboard.html page
		return "dashboard";
	}
	@GetMapping("/enquiry")
	public String addEnquiryPage() {
		return "addEnquiry";
	}
	@GetMapping("/enquires")
	public String viewEnquiryPage() {
		return "viewEnquiry";
	}

}
