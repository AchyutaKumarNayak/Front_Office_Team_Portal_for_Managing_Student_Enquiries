package in.achyuta.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import in.achyuta.bindings.LoginForm;
import in.achyuta.bindings.SignUpForm;
import in.achyuta.bindings.UnlockForm;
import in.achyuta.entity.UserEntity;
import in.achyuta.util.EmailUtils;
import in.achyuta.util.PasswordUtils;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private EmailUtils email;

	@Override
	public String login(LoginForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean signUp(SignUpForm form) {
		// TODO : Copy property from Entity to form Binding
		UserEntity entity= new UserEntity();
		BeanUtils.copyProperties(form, entity);
		
		//Create random temp password
		String pwd = PasswordUtils.getTempPassword();
		entity.setPassword(pwd);
		//By default set AccStatus as Locked
		entity.setAccountStatus("LOCKED");
		
		//Sending password to the User through mail and bind to the Object
		
		String to=form.getUserEmail();
		String subject="Your Temporary Password is send to your mail";
		StringBuffer body= new StringBuffer("");
		body.append("<h1>Your temporary password is : </h1>"+pwd);
		body.append("<a href=https://localhost:8080/signup> Click here to Unlock your Account</a>");
		//email.sendMail(to, subject, body);
		//
		
		return false;
	}

	@Override
	public boolean unlock(UnlockForm form) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String fotgot(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
