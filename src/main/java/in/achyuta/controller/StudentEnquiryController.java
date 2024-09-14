package in.achyuta.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.achyuta.bindings.DashboardResponse;
import in.achyuta.bindings.EnquirySearchCriteria;
import in.achyuta.bindings.StudentEnquiryForm;
import in.achyuta.entity.StudentEnquiryEntity;
import in.achyuta.service.StudentEnquiryService;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentEnquiryController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private StudentEnquiryService studentEnquiryService;
	
	
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
	public String addEnquiryPage(Model model) {
		//get courses dropdown values
		List<String> coursesName = studentEnquiryService.getCoursesName();
		//get enquiry statuses dropdown values
	    List<String> enquiriesStatus = studentEnquiryService.getEnquiriesStatus();
	    //Create form binding Object
	    StudentEnquiryForm studentEnquiryForm=new StudentEnquiryForm();
	    //add binding obj and course dropdown and status dropdown to model
	    model.addAttribute("coursesName", coursesName);
	    model.addAttribute("enquiriesStatus", enquiriesStatus);
	    model.addAttribute("addEnquiry", studentEnquiryForm);
		return "addEnquiry";
	}
	@PostMapping("/enquiry")
	public String addEnquiryPageHandle(@ModelAttribute("addEnquiry") StudentEnquiryForm form,Model model) {
		System.out.println(form);
		boolean status = studentEnquiryService.upsertEnquiry(form);
		if(status) {
			model.addAttribute("succMsg", "Deatils added Succesfully");
		}else {
			model.addAttribute("errMsg", "Problem occured while saving details");
		}
		return "addEnquiry";
	}
	private void initForm(Model model) {
		List<String> coursesName = studentEnquiryService.getCoursesName();
		List<String> enquiriesStatus = studentEnquiryService.getEnquiriesStatus();
		StudentEnquiryForm studentEnquiryForm=new StudentEnquiryForm();
		
		model.addAttribute("coursesName", coursesName);
		model.addAttribute("enquiriesStatus",enquiriesStatus);
		model.addAttribute("studentEnquiryForm", studentEnquiryForm);
	}
	@GetMapping("/enquires")
	public String viewEnquiryPage(Model model) {
		initForm(model);
		List<StudentEnquiryEntity> enquiries = studentEnquiryService.viewEnquires();
		model.addAttribute("enquiries", enquiries);
		
		return "viewEnquiry";
	}
	@GetMapping("filter-enquiries")
    public String filteredEnquiries(@RequestParam String courseName,
    		                        @RequestParam String enqSts,
    		                        @RequestParam String courseMode,Model model) {
    	EnquirySearchCriteria search= new EnquirySearchCriteria();
    	search.setCourseName(courseName);
    	search.setEnquiryStatus(enqSts);
    	search.setCourseMode(courseMode);
    	
    	System.out.println(search);
    	//Get the session id for specifeid user(Successful Logger) by using the key name 
        //in.achyuta.UserServiceImpl  line no 41
    	Integer userId =(Integer)session.getAttribute("userId");
    	List<StudentEnquiryEntity> viewFilteredEnquiries = studentEnquiryService.viewFilteredEnquiries(search, userId);
    	model.addAttribute("enquiries", viewFilteredEnquiries);
    	return "filterEnquiry";
    }
    

	

}
