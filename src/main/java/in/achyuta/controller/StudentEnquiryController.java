package in.achyuta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentEnquiryController {
	
	
	@GetMapping("/dashboard")
	public String dashboardPage() {
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
