package in.achyuta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.achyuta.bindings.DashboardResponse;
import in.achyuta.bindings.EnquirySearchCriteria;
import in.achyuta.bindings.StudentEnquiryForm;
import in.achyuta.entity.CourseEntity;
import in.achyuta.entity.EnquiryStatusEntity;
import in.achyuta.entity.StudentEnquiryEntity;
import in.achyuta.entity.UserEntity;
import in.achyuta.repo.CourseRepo;
import in.achyuta.repo.EnquiryStatusRepo;
import in.achyuta.repo.StudentEnquiryRepo;
import in.achyuta.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
@Service
public class StudentEnquiryServiceImpl implements StudentEnquiryService {
	
	@Autowired
    private UserRepo userRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnquiryStatusRepo enquiryStatusRepo;
	
	@Autowired
	private StudentEnquiryRepo studentEnquiryRepo;
	
	@Autowired
	private HttpSession session;
	

	@Override
	public List<String> getCoursesName() {
		
		List<CourseEntity> all = courseRepo.findAll();
		List<String> course=new ArrayList<>();
		for(CourseEntity courseEntity:all) {
			course.add(courseEntity.getCourseName());
		}
		
		return course;
	}

	@Override
	public List<String> getEnquiriesStatus() {
		List<EnquiryStatusEntity> all = enquiryStatusRepo.findAll();
		List<String> status=new ArrayList<>();
		for(EnquiryStatusEntity enquiryStatusEntity:all) {
			status.add(enquiryStatusEntity.getStatusName());
		}
		
		return status;
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
			                        .filter(e->e.getEnquiryStatus().equals("Enrolled"))
			                        .collect(Collectors.toList()).size();
		  //To display total no Lost Enquiries(whose enquiryStatus is LOST)
		    Integer lostCount=	enquires.stream()
                               .filter(e->e.getEnquiryStatus().equals("Lost"))
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
	public boolean upsertEnquiry(StudentEnquiryForm form) {
		//to save enquiry in StudentEnquiryEntity table
		StudentEnquiryEntity enquiryEntity=new StudentEnquiryEntity();
		//to copy data from binding class StudentEnquiryForm to entity class StudentEnquiryEntity for insertint purpose
		BeanUtils.copyProperties(form, enquiryEntity);
		//to specify the above enquiries is belongs to specified  user
		Integer userId = (Integer)session.getAttribute("userId");
		//get the specified user bsed on session userId
		UserEntity userEntity = userRepo.findById(userId).get();
		//Set the value of user field in StudentEnquiryEntity data with
        //userEntity specified user based on session userId
		enquiryEntity.setUser(userEntity);
		//Save the enquiry deatails in StudentEnquiryEntity baased on specified login user
		studentEnquiryRepo.save(enquiryEntity);
		return true;
	}

	@Override
	public List<StudentEnquiryEntity> viewEnquires() {
		Integer userId = (Integer)session.getAttribute("userId");
		Optional<UserEntity> byId = userRepo.findById(userId);
		if(byId.isPresent()) {
			UserEntity userEntity = byId.get();
			List<StudentEnquiryEntity> enquires = userEntity.getEnquires();
			return enquires;
		}
		return null;
	}
	@Override
	public List<StudentEnquiryEntity> viewFilteredEnquiries(EnquirySearchCriteria search, Integer userId) {
		Optional<UserEntity> byId = userRepo.findById(userId);
		if(byId.isPresent()) {
			UserEntity userEntity = byId.get();
			List<StudentEnquiryEntity> enquires = userEntity.getEnquires();
			if(null!=search.getCourseName() && !"".equals(search.getCourseName())) {
				enquires =	enquires.stream()
				            .filter(e->e.getCourseName().equals(search.getCourseName()))
				            .collect(Collectors.toList());
			}
			if(null!=search.getEnquiryStatus() && !"".equals(search.getEnquiryStatus())) {
				enquires =   enquires.stream()
				            .filter(e->e.getEnquiryStatus().equals(search.getEnquiryStatus()))
				            .collect(Collectors.toList());
				            
			}
			if(null!=search.getCourseMode() && !"".equals(search.getCourseMode())) {
				enquires =   enquires.stream()
				                     .filter(e->e.getCourseMode().equals(search.getCourseMode()))
				                     .collect(Collectors.toList());
				            
			}
				
			return enquires;
		}	
		return null;
	}

	@Override
	public StudentEnquiryForm editEnquiry(Integer enquiryId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
