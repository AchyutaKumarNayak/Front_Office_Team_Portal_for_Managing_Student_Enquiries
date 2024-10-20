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
import in.achyuta.constants.AppConstants;
import in.achyuta.entity.StudentEnquiryEntity;
import in.achyuta.service.StudentEnquiryService;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentEnquiryController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private StudentEnquiryService studentEnquiryService;
	
	
	@GetMapping(AppConstants.STD_ENQ_CONTROLLER_MAPPING_LOGOUT)
    public String logout() {
		session.invalidate();
    	return AppConstants.INDEX_CONTROLLER_INDEX;
    }
	
	
	@GetMapping(AppConstants.STD_ENQ_CONTROLLER_MAPPING_DASHBOARD)
	public String dashboardPage(Model model) {
		//Get the session id for specifeid user(Successful Logger) by using the key name 
		//in.achyuta.UserServiceImpl  line no 41
		Integer userId =(Integer)session.getAttribute(AppConstants.SESSION_USER_ID);
	    //Call StudentEnquiryService method for business logic
		DashboardResponse dashboardRes = studentEnquiryService.getDashboardRes(userId);
		//Add this binding class in UI
		model.addAttribute(AppConstants.STUDENT_ENQUIRY_CONTROLLER_DASHBOARD_RES, dashboardRes);
		//return dashboard.html page
		return AppConstants.STUDENT_ENQUIRY_CONTROLLER_DASHBOARD;
	}
	@GetMapping(AppConstants.STD_ENQ_CONTROLLER_MAPPING_ENQUIRY)
	public String addEnquiryPage(Model model) {
		//get courses dropdown values
		List<String> coursesName = studentEnquiryService.getCoursesName();
		//get enquiry statuses dropdown values
	    List<String> enquiriesStatus = studentEnquiryService.getEnquiriesStatus();
	    //Create form binding Object
	    StudentEnquiryForm studentEnquiryForm=new StudentEnquiryForm();
	    //add binding obj and course dropdown and status dropdown to model
	    model.addAttribute(AppConstants.STUDENT_ENQUIRY_CONTROLLER_COURSRES_NAME, coursesName);
	    model.addAttribute(AppConstants.STUDENT_ENQUIRY_CONTROLLER_ENQS_STS, enquiriesStatus);
	    model.addAttribute(AppConstants.STUDENT_ENQUIRY_CONTROLLER_ADD_ENQS, studentEnquiryForm);
		return AppConstants.STUDENT_ENQUIRY_CONTROLLER_ADD_ENQS;
	}
	@PostMapping(AppConstants.STD_ENQ_CONTROLLER_MAPPING_ENQUIRY)
	public String addEnquiryPageHandle(@ModelAttribute(AppConstants.STUDENT_ENQUIRY_CONTROLLER_ADD_ENQS) StudentEnquiryForm form,Model model) {
		System.out.println(form);
		boolean status = studentEnquiryService.upsertEnquiry(form);
		if(status) {
			model.addAttribute(AppConstants.SUCCESS_MSG_KEY, AppConstants.SUCCESS_MSG_ENQ_VALUE);
		}else {
			model.addAttribute(AppConstants.ERR_MSG_KEY, AppConstants.ERR_MSG_ENQ_VALUE);
		}
		return AppConstants.STUDENT_ENQUIRY_CONTROLLER_ADD_ENQS;
	}
	private void initForm(Model model) {
		List<String> coursesName = studentEnquiryService.getCoursesName();
		List<String> enquiriesStatus = studentEnquiryService.getEnquiriesStatus();
		StudentEnquiryForm studentEnquiryForm=new StudentEnquiryForm();
		
		model.addAttribute(AppConstants.STUDENT_ENQUIRY_CONTROLLER_COURSRES_NAME, coursesName);
		model.addAttribute(AppConstants.STUDENT_ENQUIRY_CONTROLLER_ENQS_STS,enquiriesStatus);
		model.addAttribute(AppConstants.STUDENT_ENQUIRY_CONTROLLER_STUDENT_ENQ_FORM, studentEnquiryForm);
	}
	@GetMapping(AppConstants.STD_ENQ_CONTROLLER_MAPPING_ENQUIRIES)
	public String viewEnquiryPage(Model model) {
		initForm(model);
		List<StudentEnquiryEntity> enquiries = studentEnquiryService.viewEnquires();
		model.addAttribute(AppConstants.STUDENT_ENQUIRY_CONTROLLER_STUDENT_ENQS, enquiries);
		
		return AppConstants.STUDENT_ENQUIRY_CONTROLLER_STUDENT_VIEW_ENQ;
	}
	@GetMapping(AppConstants.STUDENT_ENQUIRY_CONTROLLER_STUDENT_FILTER_ENQ)
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
    	Integer userId =(Integer)session.getAttribute(AppConstants.SESSION_USER_ID);
    	List<StudentEnquiryEntity> viewFilteredEnquiries = studentEnquiryService.viewFilteredEnquiries(search, userId);
    	model.addAttribute(AppConstants.STUDENT_ENQUIRY_CONTROLLER_STUDENT_ENQS, viewFilteredEnquiries);
    	return AppConstants.STUDENT_ENQUIRY_CONTROLLER_STUDENT_FILT_ENQ;
    }
    

	

}
