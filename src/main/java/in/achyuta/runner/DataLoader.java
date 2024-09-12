package in.achyuta.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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
		c1.setCourseName("Java");
		CourseEntity c2= new CourseEntity();
		c2.setCourseName("Python");
		CourseEntity c3= new CourseEntity();
		c3.setCourseName("AWS");
		CourseEntity c4= new CourseEntity();
		c4.setCourseName("DevOps");
		
		courseRepo.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		
		enquiryStatusRepo.deleteAll();
		
		EnquiryStatusEntity e1=new EnquiryStatusEntity();
		e1.setStatusName("New");
		EnquiryStatusEntity e2=new EnquiryStatusEntity();
		e2.setStatusName("Enrolled");
		EnquiryStatusEntity e3=new EnquiryStatusEntity();
		e3.setStatusName("Lost");
		
		enquiryStatusRepo.saveAll(Arrays.asList(e1,e2,e3));
		
		
		
		
		
		
	}

}
