package in.achyuta.service;

import in.achyuta.bindings.LoginForm;
import in.achyuta.bindings.SignUpForm;
import in.achyuta.bindings.UnlockForm;


public interface UserService {
	
	//for login page 
	public String login(LoginForm form);
	
	//for sign up page
	public boolean signUp(SignUpForm form);
	
	//For unlock page
	public boolean unlock(UnlockForm form);
	
	//for forgotPwd page
	public String fotgot(String email);

}
