package in.achyuta.service;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.achyuta.bindings.LoginForm;
import in.achyuta.bindings.SignUpForm;
import in.achyuta.bindings.UnlockForm;
import in.achyuta.entity.UserEntity;
import in.achyuta.repo.UserRepo;
import in.achyuta.util.EmailUtils;
import in.achyuta.util.PasswordUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailUtils email;

	@Override
	public String login(LoginForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean signUp(SignUpForm form) {
		//TODO: Check this user email is present in database or not if present return false
		UserEntity user = userRepo.findByUserEmail(form.getUserEmail());
		if(null!=user) {
			return false;
		}
		
		// TODO : Copy property from binding obj to entity obj
		UserEntity entity= new UserEntity();
		BeanUtils.copyProperties(form, entity);
		
		//TODO :Create random temp password
		String pwd = PasswordUtils.getTempPassword();
		entity.setPassword(pwd);
		//By default set AccStatus as Locked
		entity.setAccountStatus("LOCKED");
		//TODO :Insert into DB
		userRepo.save(entity);
		
		
		//TODO :Sending password to the User through mail and bind to the Object
		
		String to=form.getUserEmail();
		String subject="Your Temporary Password is send to your mail";
		StringBuffer body= new StringBuffer("");
		body.append("<h1>Unlock your account by using below temporary password </h1>");
		body.append("Your Temporary password is : "+pwd);
		body.append("<br/>");
		body.append("<a href=http://localhost:8080/unlock?email="+to+"> Click here to Unlock your Account</a>");
		email.sendMail(to, subject, body.toString());
		
		return true;
	}

	@Override
	public boolean unlock(UnlockForm form) {
		UserEntity user = userRepo.findByUserEmail(form.getUserEmail());
		 if((user.getPassword()).equals(form.getTempPassword())) {
			 user.setPassword(form.getNewPassword());
			 user.setAccountStatus("UNLOCKED");
			 userRepo.save(user);
			 return true;
		 }else {
			 return false;
		 }	
	}

	@Override
	public String fotgot(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
