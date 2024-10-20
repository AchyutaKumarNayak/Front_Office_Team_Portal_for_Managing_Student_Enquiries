package in.achyuta.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.achyuta.constants.AppConstants;
import in.achyuta.entity.CourseEntity;
import in.achyuta.entity.EnquiryStatusEntity;
import in.achyuta.repo.CourseRepo;
import in.achyuta.repo.EnquiryStatusRepo;

@Component
public class DataLoader implements ApplicationRunner{
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnquiryStatusRepo enquiryStatusRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		courseRepo.deleteAll();
		
		CourseEntity c1= new CourseEntity();
		c1.setCourseName(AppConstants.RUNNER_COURSE_NAME1);
		CourseEntity c2= new CourseEntity();
		c2.setCourseName(AppConstants.RUNNER_COURSE_NAME2);
		CourseEntity c3= new CourseEntity();
		c3.setCourseName(AppConstants.RUNNER_COURSE_NAME3);
		CourseEntity c4= new CourseEntity();
		c4.setCourseName(AppConstants.RUNNER_COURSE_NAME4);
		
		courseRepo.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		
		enquiryStatusRepo.deleteAll();
		
		EnquiryStatusEntity e1=new EnquiryStatusEntity();
		e1.setStatusName(AppConstants.RUNNER_STS1);
		EnquiryStatusEntity e2=new EnquiryStatusEntity();
		e2.setStatusName(AppConstants.RUNNER_STS2);
		EnquiryStatusEntity e3=new EnquiryStatusEntity();
		e3.setStatusName(AppConstants.RUNNER_STS3);
		
		enquiryStatusRepo.saveAll(Arrays.asList(e1,e2,e3));
		
		
		
		
		
		
	}

}
