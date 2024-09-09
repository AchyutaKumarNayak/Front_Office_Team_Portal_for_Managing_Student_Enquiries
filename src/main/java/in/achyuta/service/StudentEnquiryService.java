package in.achyuta.service;

import java.util.List;


import in.achyuta.bindings.DashboardResponse;
import in.achyuta.bindings.EnquirySearchCriteria;
import in.achyuta.bindings.StudentEnquiryForm;


public interface StudentEnquiryService {
	
	//For getting course name from dropdown in addenquiry page
	public List<String> getCourseName();
	
	//For getting enuiry status name from dropdown in addenquiry page
	public List<String> getEnquiryStatus();
	
	//Getting Dashboard based on user id
	public DashboardResponse getDashboardRes(Integer userId);
	
	//For addEnquiry page adding Student enquiry
	public String upsertEnquiry(StudentEnquiryForm form);
	
	//getting StudentEnquiryForm at the page of ViewEnquiries
	public List<StudentEnquiryForm> viewEnquires(Integer userId,EnquirySearchCriteria search);
	
	//When click on the editbutton at viewEnquiry page it should take the enquiryId as parameter to edit the enquiryStatus
	public StudentEnquiryForm editEnquiry(Integer enquiryId);

}
