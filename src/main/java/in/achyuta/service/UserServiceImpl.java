package in.achyuta.service;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.achyuta.bindings.LoginForm;
import in.achyuta.bindings.SignUpForm;
import in.achyuta.bindings.UnlockForm;
import in.achyuta.constants.AppConstants;
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
			return AppConstants.USER_SERVICE_ERR_LOGIN_VALUE;
		}
		if ((user.getAccountStatus()).equals(AppConstants.USER_SERVICE_LOCKED)){
			return AppConstants.USER_SERVICE_ERR_LOCKED_VALUE;
		}
		//here the succesful login of our user 
		//herre we have to create session for user based on his userId that every user data is remember by the application
		//ande based on usewr login correspond user data is display
		session.setAttribute(AppConstants.SESSION_USER_ID, user.getUserId());
		
		return AppConstants.USER_CONTROLLER_SUCCESS;
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
		entity.setAccountStatus(AppConstants.USER_SERVICE_LOCKED);
		//TODO :Insert into DB
		userRepo.save(entity);
		
		
		//TODO :Sending password to the User through mail and bind to the Object
		
		String to=form.getUserEmail();
		String subject=AppConstants.USER_SERVICE_EMAIL_SUB;
		StringBuffer body= new StringBuffer(AppConstants.USER_SERVICE_EMPTY_STR);
		body.append(AppConstants.USER_SERVICE_EMAIL_BODY1);
		body.append(AppConstants.USER_SERVICE_EMAIL_BODY2+pwd);
		body.append(AppConstants.USER_SERVICE_EMAIL_BODY3);
		body.append(AppConstants.USER_SERVICE_EMAIL_BODY4+to+AppConstants.USER_SERVICE_EMAIL_BODY5);
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
			 user.setAccountStatus(AppConstants.USER_SERVICE_UNLOCKED);
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
		String subject=AppConstants.USER_SERVICE_FORGOT_EMAIL_SUB;
		String body=user.getPassword();
		email.sendMail(userEmail, subject, body);
		return true;
	}

}
