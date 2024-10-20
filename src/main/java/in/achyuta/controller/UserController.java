package in.achyuta.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.achyuta.bindings.LoginForm;
import in.achyuta.bindings.SignUpForm;
import in.achyuta.bindings.UnlockForm;
import in.achyuta.constants.AppConstants;
import in.achyuta.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	
	@GetMapping(AppConstants.USER_CONTROLLER_MAPPING_SIGNUP)
	public String signUpPage(Model model) {
		//when my sign up page is loading  i want to send my empty binding Obj
		model.addAttribute(AppConstants.USER_CONTROLLER_USER, new SignUpForm());//key(th:object), new empty binding Obj
		return AppConstants.USER_CONTROLLER_SIGNUP;
	}
	
	@PostMapping(AppConstants.USER_CONTROLLER_MAPPING_SIGNUP)
	public String signUpPage(@ModelAttribute(AppConstants.USER_CONTROLLER_USER) SignUpForm form,Model model) {
		boolean status = userService.signUp(form);
		if (status) {
			model.addAttribute(AppConstants.SUCCESS_MSG_KEY,AppConstants.SUCCESS_MSG_SIGNUP_VALUE);
		}
		else {
			model.addAttribute(AppConstants.ERR_MSG_KEY,AppConstants.ERR_MSG_SIGNUP_VALUE);
		}
		return AppConstants.USER_CONTROLLER_SIGNUP;
	}
	
	@GetMapping(AppConstants.USER_CONTROLLER_MAPPING_UNLOCK)
	public String unlockPage(@RequestParam String email,Model model) {
		
		UnlockForm unlockForm= new UnlockForm();
		unlockForm.setUserEmail(email);
		model.addAttribute(AppConstants.USER_CONTROLLER_UNLOCK, unlockForm);
		return AppConstants.USER_CONTROLLER_UNLOCK;
	}
	@PostMapping(AppConstants.USER_CONTROLLER_MAPPING_UNLOCK)
	public String unlockUserAccount(@ModelAttribute(AppConstants.USER_CONTROLLER_UNLOCK) UnlockForm form, Model model) {
		
		if((form.getNewPassword()).equals(form.getConfirmPassword())) {
			boolean status = userService.unlock(form);
			if(status) {
				model.addAttribute(AppConstants.SUCCESS_MSG_KEY, AppConstants.SUCCESS_MSG_UNLOCK_VALUE);
			}else {
				model.addAttribute(AppConstants.ERR_MSG_KEY, AppConstants.ERR_MSG_UNLOCK_VALUE1);
			}
		}else {
			model.addAttribute(AppConstants.ERR_MSG_KEY,AppConstants.ERR_MSG_UNLOCK_VALUE2 );
		}
//		System.out.println(form);
		return AppConstants.USER_CONTROLLER_UNLOCK;
	}
	
	@GetMapping("/login")
	public String logInPage(Model model){
		 model.addAttribute(AppConstants.USER_CONTROLLER_LOGIN_FORM, new LoginForm());
		return AppConstants.USER_CONTROLLER_LOGIN;
	}
	@PostMapping(AppConstants.USER_CONTROLLER_MAPPING_LOGIN)
	public String logInPageHandle(@ModelAttribute(AppConstants.USER_CONTROLLER_LOGIN) LoginForm form,Model model){
		String status = userService.login(form);
		if(status.contains(AppConstants.USER_CONTROLLER_SUCCESS)) {
			return "redirect:/dashboard";
		}else {
			model.addAttribute(AppConstants.ERR_MSG_KEY, status);
		}
		
		return AppConstants.USER_CONTROLLER_LOGIN;
	}
	
	
	@GetMapping(AppConstants.USER_CONTROLLER_MAPPING_FORGOT)
	public String forgotPwdPage() {
		return AppConstants.USER_CONTROLLER_FORGOT_PWD;
	}
	@PostMapping("/forgot")
	public String forgotPwdPageHandle(@RequestParam(AppConstants.USER_CONTROLLER_EMAIL) String email,Model model) {
		System.out.println(email);
		boolean status = userService.fotgot(email);
		if (status) {
			model.addAttribute(AppConstants.SUCCESS_MSG_KEY, AppConstants.SUCCESS_MSG_FORGOT_VALUE+email);
		}else {
			model.addAttribute(AppConstants.ERR_MSG_KEY,AppConstants.ERR_MSG_FORGOT_VALUE);
		}
		return AppConstants.USER_CONTROLLER_FORGOT_PWD;
	}
	
}
