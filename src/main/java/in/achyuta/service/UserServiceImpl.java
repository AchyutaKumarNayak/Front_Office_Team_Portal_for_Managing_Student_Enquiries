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
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EmailUtils email;

	@Override
	public String login(LoginForm form) {
		UserEntity user = userRepo.findByUserEmailAndPassword(form.getUserEmail(), form.getPassword());
		if(null==user) {
			return "Invalid Credentials";
		}
		if ((user.getAccountStatus()).equals("LOCKED")){
			return "Your Account is Locked";
		}
		//here the succesful login of our user 
		//herre we have to create session for user based on his userId that every user data is remember by the application
		//ande based on usewr login correspond user data is display
		session.setAttribute("userId", user.getUserId());
		
		return "success";
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
		//Getting the user data based on userEmail 
		UserEntity user = userRepo.findByUserEmail(form.getUserEmail());
		//if user password retrived based on user object is same with temp password given by user in frontend page 
		 if((user.getPassword()).equals(form.getTempPassword())) {
			 //set userPassword with given newPasswordgiven by the user
			 user.setPassword(form.getNewPassword());
			 //Set accountStatus as unlocked
			 user.setAccountStatus("UNLOCKED");
			 //save the updated user in DB 
			 userRepo.save(user);
			 return true;
		 }else {
			 return false;
		 }	
	}

	@Override
	public boolean fotgot(String userEmail) {
		//check the data is present in the Db with given Email
		UserEntity user = userRepo.findByUserEmail(userEmail);
		//if not present return false
		if(user==null) {
			return false;
		}
		//if present send to mail with password of the corresponding mail and return true
		String subject="Recovery of password";
		String body=user.getPassword();
		email.sendMail(userEmail, subject, body);
		return true;
	}

}
