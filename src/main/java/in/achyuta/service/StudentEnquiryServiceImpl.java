package in.achyuta.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.achyuta.bindings.DashboardResponse;
import in.achyuta.bindings.EnquirySearchCriteria;
import in.achyuta.bindings.StudentEnquiryForm;
import in.achyuta.entity.StudentEnquiryEntity;
import in.achyuta.entity.UserEntity;
import in.achyuta.repo.UserRepo;
@Service
public class StudentEnquiryServiceImpl implements StudentEnquiryService {
	
	@Autowired
    private UserRepo userRepo;
	

	@Override
	public List<String> getCourseName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getEnquiryStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DashboardResponse getDashboardRes(Integer userId) {
		//Getting the userEntity object based on primary key
		//It returns Optional means  based on userId primary key data may be present or not
		Optional<UserEntity> byId = userRepo.findById(userId);
		//Creating binding class DashboardResponse Object to set fields based on retreive record 
		DashboardResponse response=new DashboardResponse();
		//if data is present based on primary key 
		if(byId.isPresent()) {
			//get the data
			UserEntity userEntity = byId.get();
			//get the collection of enquiries by using UserEntity referenced variable of that
			//specified userEntity which is retrieved based on primary key
			List<StudentEnquiryEntity> enquires = userEntity.getEnquires();
			//To display total no of enquires of that login(specified) user
			Integer totalEnquiriesCount = enquires.size();
			//To display total no Enrolled Enquiries(whose enquiryStatus is ENROLLED)
		    Integer enrolledCount=	enquires.stream()
			                        .filter(e->e.getEnquiryStatus().equals("ENROLLED"))
			                        .collect(Collectors.toList()).size();
		  //To display total no Lost Enquiries(whose enquiryStatus is LOST)
		    Integer lostCount=	enquires.stream()
                               .filter(e->e.getEnquiryStatus().equals("LOST"))
                               .collect(Collectors.toList()).size();
		    
		   //Set this values to binding class DashboardResponse to send it to controller 
		    response.setTotalEnquiriesCount(totalEnquiriesCount);
		    response.setEnrolledCount(enrolledCount);
		    response.setLostCount(lostCount);
		}
		//Sending DashboardRespone object with setted values
		return response;
	}

	@Override
	public String upsertEnquiry(StudentEnquiryForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentEnquiryForm> viewEnquires(Integer userId, EnquirySearchCriteria search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentEnquiryForm editEnquiry(Integer enquiryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
